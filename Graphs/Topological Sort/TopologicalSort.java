
import java.lang.reflect.Array;
import java.util.*;

public class TopologicalSort {

    public static List<Integer> topoSort(List<List<Integer>> pre_requisites, int total_courses) {
        Stack<Integer> enrollOrder = new Stack<Integer>();
		Map<Integer, List<Integer>> allPreReq = getAllPreReqCourses(pre_requisites);

        //given, courses lie in range [0,total_courses)
        for (int c = 0; c < total_courses; c++) {
            enrollOrder = topoUtil(allPreReq, enrollOrder, c);
         }
        return enrollOrder;
    }

    private  static  Map<Integer, List<Integer>> getAllPreReqCourses(List<List<Integer>> pre_requisites){
        Map<Integer, List<Integer>> preReqMap = new HashMap<Integer, List<Integer>>();

        for (int i= 0; i < pre_requisites.size(); i++) {
            List<Integer> preReqCoursesList = new ArrayList<>();
            List<Integer> preReqElement = pre_requisites.get(i);
            int mainCourse = preReqElement.get(1);
            int preReqCourse = preReqElement.get(0);
            List<Integer> oldPreReqList = preReqMap.get(mainCourse)!=null ? preReqMap.get(mainCourse) : preReqCoursesList;
            oldPreReqList.add(preReqCourse);
            preReqMap.put(mainCourse, oldPreReqList);
        }
        return  preReqMap;
    }

    private  static List<Integer> getPreReqCourses(List<List<Integer>> pre_requisites, int course){
        List<Integer> preReqCoursesList = new ArrayList<>();
        for (int i= 0; i < pre_requisites.size(); i++) {
            List<Integer> preReqElement = pre_requisites.get(i);
            int mainCourse = preReqElement.get(1);
            int preReqCourse = preReqElement.get(0);
            if (mainCourse == course) {
                preReqCoursesList.add(preReqCourse);
            }
        }
        return  preReqCoursesList;
    }

    private static Stack<Integer> topoUtil2(List<List<Integer>> pre_requisites, Stack<Integer> enrollOrder, int course){
        if (!enrollOrder.contains(course)) {
            List<Integer> emptyPreReqList = new ArrayList<>();
            List<Integer> preReqCourses = getPreReqCourses(pre_requisites, course);
                for (int i=0;i < preReqCourses.size();i++) {
                    enrollOrder =  topoUtil2(pre_requisites, enrollOrder, preReqCourses.get(i));
                }
                enrollOrder.push(course);
		}
        return  enrollOrder;
    }

    private static Stack<Integer> topoUtil(Map<Integer, List<Integer>> preReqMap, Stack<Integer> enrollOrder, int course){
        if (!enrollOrder.contains(course)) {
            List<Integer> preReqCourses = preReqMap.get(course);
            List<Integer> emptyPreReqList = new ArrayList<>();
            if(preReqCourses == null)
                preReqCourses = emptyPreReqList;

            for (int i=0;i < preReqCourses.size();i++) {
                enrollOrder =  topoUtil(preReqMap, enrollOrder, preReqCourses.get(i));
            }
            enrollOrder.push(course);
        }
        return  enrollOrder;
    }
}


