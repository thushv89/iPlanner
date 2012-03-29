/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iplanner_v2;

import java.util.ArrayList;

/**
 *
 * @author Thushan
 */
public class NaiveBayesImplementer {

    ArrayList<ArrayList<Integer>> trainingKeyWordData;  //Training data keywords input matrix
    ArrayList<ArrayList<Integer>> trainingPriorityData; //Training data priority input matrix
    ArrayList<ArrayList<Integer>> trainingOutputData;   //Training data output vector
    //1 corrensponds to 8.00am-9.00am, 2 -> 9.00am to 10.am ...
    private static int[] classes = {1, 2, 3, 4, 5, 6, 7, 8, 9};   //set of classes which the situation is matched to

    public NaiveBayesImplementer() {
        //Initialization
        trainingKeyWordData = new ArrayList<ArrayList<Integer>>();
        trainingPriorityData = new ArrayList<ArrayList<Integer>>();
        trainingOutputData=new ArrayList<ArrayList<Integer>>();
        //each of traning input matrix contain 5 rows corresponding to monday, tuesday, ..., friday
        for (int i = 0; i < 5; i++) {
            trainingKeyWordData.add(new ArrayList<Integer>());
            trainingPriorityData.add(new ArrayList<Integer>());
            trainingOutputData.add(new ArrayList<Integer>());
        }
        classifyTrData(IOHandler.readTrainingSet("training_data.tr").getAl());
    }

    //fill the matrices with the training data
    private void classifyTrData(ArrayList<Meeting> trData) {
        //put each meeting m to its corresponding position in the matrices
        for (Meeting m : trData) {
            trainingKeyWordData.get(m.getDay()).add(m.getMeetingWith().ordinal());
            trainingPriorityData.get(m.getDay()).add(m.getPriority());
            trainingOutputData.get(m.getDay()).add(m.getTimeSlot());
        }

    }

    //update the training set
    public void updateTrainingSet() {
    }

    //Important part, given a situation to the algorithm find the best matching time-slots.
    public ArrayList<String> getPrediction(int keyword,int priority) {
        //int[] keywords=	 {1,2,3,1,2,3,1,2,3,3,3,1,3,1,1,3,3};   //keywords training set
        //int[] priorities={0,0,0,1,1,1,2,2,2,2,0,2,0,2,2,2,1};   //priority training set
        //int[] y=         {1,2,3,4,5,9,7,8,2,3,3,4,5,7,7,8,9};   //output per each training sample

        //initialize matrices for conditional probabilities
        ArrayList<double[]> condProbKW = new ArrayList<double[]>();
        ArrayList<double[]> condProbPrio = new ArrayList<double[]>();
        ArrayList<double[]> prioProbs = new ArrayList<double[]>();

        //calculate the normal probability for all classes
        ArrayList<double[]> normProbAllClasses = new ArrayList<double[]>();

        //do for all 5 days...
        for (int i = 0; i < 5; i++) {
            //get the prior probabilities
            prioProbs.add(getPriorProbs(trainingOutputData.get(i)));
        }

        //do for all 5 days...
        for (int i = 0; i < 5; i++) {
            //get the conditional probabilities
            condProbKW.add(getCondProb(trainingOutputData.get(i), trainingKeyWordData.get(i), keyword));
            condProbPrio.add(getCondProb(trainingOutputData.get(i), trainingPriorityData.get(i), priority));

        }

        //get normalized probabilities for each classe for 5 days
        for (int i = 0; i < 5; i++) {
            normProbAllClasses.add(getNormProb(condProbKW.get(i), condProbPrio.get(i), prioProbs.get(i)));
        }

        ArrayList<String> timeSlots=CommonUtil.getAvailableTimeSlots();
        ArrayList<String> maxTimeSlots=new ArrayList<String>();
        //int[] testArr=new int[5];
        for(int i=0;i<5;i++){
            //testArr[i]=maxIdx(normProbAllClasses.get(i));
            maxTimeSlots.add(timeSlots.get(maxIdx(normProbAllClasses.get(i))));
            //System.out.println(testArr[i]);
        }
        //return the max probability time slots for each of 5 days
        return maxTimeSlots;


    }

    //given a double[] get the index of the maximum element
    private int maxIdx(double[] array) {
        double max = array[0];
        int classNum = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                classNum = i + 1;
            }
        }
        return classNum;
    }

    //Calculate P(y1 | attr1, attr2), P(y2 | attr1,attr2), ... and Get normalized probabilities
    //That is given a situation, we try to find the probability of happening y1
    private double[] getNormProb(double[] probKeyWd, double[] probPrio, double[] priorProbs) {
        double total = 0.0;
        double[] probAll = new double[CommonUtil.NUM_CLASSES];
        double[] normProbAll = new double[CommonUtil.NUM_CLASSES];
        for (int i = 0; i < CommonUtil.NUM_CLASSES; i++) {
            probAll[i] = probKeyWd[i] * probPrio[i] * priorProbs[i];
            total += probAll[i];
        }

        for (int i = 0; i < CommonUtil.NUM_CLASSES; i++) {
            normProbAll[i] = probAll[i] / total;
            //System.out.println(normProbAll[i]);
        }
        return normProbAll;
    }

    //get conditional probability for occurring attr if tOutput has occurredcd
    //That is get P(attr | y1) P(attr | y2) ... P(attr | y3)
    private double[] getCondProb(ArrayList<Integer> tOutput, ArrayList<Integer> tAttr, int attr) {
        int[] freq = new int[CommonUtil.NUM_CLASSES];
        int[] condFreq = new int[CommonUtil.NUM_CLASSES];
        double[] result = new double[CommonUtil.NUM_CLASSES];
        for (int i = 0; i < tOutput.size(); i++) {
            for (int j = 0; j < CommonUtil.NUM_CLASSES; j++) {
                if (tOutput.get(i) == classes[j]) {
                    freq[j]++;
                    if (tAttr.get(i) == attr) {
                        condFreq[j]++;
                    }
                }
            }
        }
        for (int i = 0; i < CommonUtil.NUM_CLASSES; i++) {
            if (freq[i] > 0) {//otherwise NaN is returned
                result[i] = 1.0 * condFreq[i] / freq[i];
            } else {
                result[i] = 0.0;
            }
        }
        return result;
    }

    //get probability for occurrence of each class in the training set
    //That is, output has P(y1),P(y2),... where y1,y2, are output classes
    private double[] getPriorProbs(ArrayList<Integer> tOutput) {
        int total = tOutput.size();
        int[] frequency = new int[CommonUtil.NUM_CLASSES];
        for (int i = 0; i < CommonUtil.NUM_CLASSES; i++) {
            frequency[i] = 0;
        }
        double[] result = new double[CommonUtil.NUM_CLASSES];
        for (int i = 0; i < tOutput.size(); i++) {
            for (int j = 0; j < CommonUtil.NUM_CLASSES; j++) {
                if (tOutput.get(i) == j + 1) {
                    frequency[j]++;
                }
            }
        }

        for (int i = 0; i < frequency.length; i++) {
            result[i] = 1.0 * frequency[i] / total;
        }

        return result;
    }
}
