package com.sonicge.dailyAlgorithm;

import java.util.*;

/**
 * 【课程表】
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-12
 */

public class Demo94 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCourses = sc.nextInt();
        int[][] prerequisites = new int[1][2];
        prerequisites[0][0] = sc.nextInt();
        prerequisites[0][1] = sc.nextInt();

        boolean flag = canFinish(numCourses, prerequisites);
        System.out.println("是否可以完成所有课程的学习？" + flag);
    }

    /**
     * 课程表
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) return false;
        int size = prerequisites.length;
        if (size == 0) return false;

        //记录课程的先修课程
        int[] inDegree = new int[numCourses];
        //记录一门课程学完之后，可以学哪些后置的课
        HashSet<Integer>[] adj = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new HashSet<>();
        }

        for (int[] arr : prerequisites) {
            inDegree[arr[0]]++;
            adj[arr[1]].add(arr[0]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int course : inDegree) {
            if (inDegree[course] == 0) {
                queue.add(course);
            }
        }

        //开始处理
        int cnt = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                cnt++;
                Integer course = queue.poll();
                for (int nextCourse : adj[course]) {
                    inDegree[nextCourse]--;
                    if (inDegree[nextCourse] == 0) {
                        queue.add(nextCourse);
                    }
                }
            }
        }
        return cnt == numCourses;
    }
}
