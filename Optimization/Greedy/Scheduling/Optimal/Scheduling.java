
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class Scheduling {
    static class Job {
        Job(int s, int f) { start = s; finish = f;}

        void setIsLateNight(boolean isTrue) {
            isLateNight=isTrue;
        }

        int start;
        int finish;
        boolean isLateNight=false;
    }

    public static int schedule(int[][] A) {
        //todo:implement sort array object based on finish time
        //todo:find if we cross 0 or not-> start > end?
        //todo:if yes, start from 0 for the day and end the last end should be less than this one's start?
        Job[] allJobs = new Job[A.length];

        LinkedList<Integer> selectedJobs;
        int totalJobs=0;

        Job firstJobOfDay = null;
        int firstJobOfDayIndex=0;
        //todo:check if we have to select first activity
        for(int i=0;i<A.length;i++){
            allJobs[i] = new Job(A[i][0], A[i][1]);
            //System.out.print("{" + A[i][0] +"," + A[i][1] + "},");
        }
//        for(int i=0;i<A.length;i++){
//            System.out.println(allJobs[i].start + ","+ allJobs[i].finish);
//        }
        sortByFinishTime(allJobs);

//        System.out.println("After sorting.............");
//        for(int i=0;i<A.length;i++){
//        System.out.println(allJobs[i].start + ","+ allJobs[i].finish);
//        }

        //setting late night
        for (int i=0;i< allJobs.length;i++) {
            if(allJobs[i].start > allJobs[i].finish) { //activity crosses midnight
                allJobs[i].isLateNight = true;
            }
        }

        //setting first job starting in day
        for (int i=0;i< allJobs.length;i++) {
            if(allJobs[i].isLateNight != true) { //activity crosses midnight
                firstJobOfDay = allJobs[i];
                totalJobs = 1; //greedily selecting first job that starts in the day
                firstJobOfDayIndex=i; //our algo should start from this index
                break;
            }
        }


//        print2DArray(A);



        //we are calling this recursive loop AFTER SELECTING THE FIRST JOB
        //we need to count this first job separately because the jobs that are checked recursively are the jobs after this
        //doesnt include this
        selectedJobs = jobScheduler(allJobs,firstJobOfDayIndex);

        totalJobs = totalJobs + selectedJobs.size();
        int lastCompletedJobBeforeMidnightIndex = selectedJobs.size() > 0 ? selectedJobs.getFirst() : firstJobOfDayIndex;
        Job lastCompletedJobBeforeMidnight =  allJobs[lastCompletedJobBeforeMidnightIndex];//last linked will be on top
        //System.out.println("start time " + lastCompletedJobBeforeMidnight.start+  " finish time of last completed job is " + lastCompletedJobBeforeMidnight.finish);

        for (int i=0;i< allJobs.length;i++) {
            if(allJobs[i].isLateNight == true) {
//                System.out.println("late night job start time " + allJobs[i].start + " end time " + allJobs[i].finish);
//                System.out.println("day first job start time is " + firstJobOfDay.start + " end time " + firstJobOfDay.finish);
                if (allJobs[i].start >= lastCompletedJobBeforeMidnight.finish) {

                    //todo:consider this condition if we only want to optimize for the current day
                    //todo:ie., we ony want to get the maximum number of jobs today and dont care whether
                    //todo:the next one starts at the same time the next day. that can be optimized separately
                       totalJobs = totalJobs + 1;
                       break; //only one of the midnight job can be scheduled. 2 midnight jobs will collide


                    //todo:consider this condition if you look at like a batch process. one that starts
                    //todo:at the same time everday and goes over to the next day and repeats
//                    if (allJobs[i].finish < firstJobOfDay.start) {
//                        totalJobs = totalJobs + 1;
//                    }
                }
            }
        }

        return totalJobs;
    }

    public static LinkedList<Integer> jobScheduler(Job[] allJobs, int k) {
        LinkedList<Integer> selectedJobs;
        int m = k + 1;
        // skip over activities that start too soon
        while (
                m < allJobs.length &&
                (allJobs[m].start < allJobs[k].finish ||
                allJobs[m].isLateNight==true)   //skipping late night jobs in this greedy algo..considering separately
        )
            m += 1;
        if (m < allJobs.length) {
            selectedJobs = jobScheduler(allJobs, m);
            selectedJobs.add(m);
            return selectedJobs;
        } else {
            return new LinkedList<Integer>();
        }
    }

    public static void sortByFinishTime(Job[] allJobs) {
        Arrays.sort(allJobs, new Comparator<Job>()
        {
            @Override
            public int compare(Job j1, Job j2)
            {
                return j1.finish - j2.finish;
            }
        });
    }

}
