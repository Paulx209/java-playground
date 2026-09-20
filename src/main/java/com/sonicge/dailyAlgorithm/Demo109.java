package com.sonicge.dailyAlgorithm;

import java.util.*;

/**
 * 【除法求值】
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，
 * 其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 *
 * 思路：有向带权图 + dfs
 *@Author: sonicge
 *@CreateTime: 2026-09-20
 */

public class Demo109 {
    static class Pair {
        String key;
        Double value;

        public Pair(String key, Double value) {
            this.key = key;
            this.value = value;
        }

        public String getKey(){
            return this.key;
        }
        public Double getValue(){
            return this.value;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        List<List<String>> equations = new ArrayList<>();
        //1.等式初始化
        for (int i = 0; i < size; i++) {
            List<String> list = new ArrayList<>();
            list.add(sc.next());
            list.add(sc.next());
            equations.add(list);
        }
        //2.values初始化
        double[] values = new double[size];
        for (int i = 0; i < size; i++) {
            values[i] = sc.nextDouble();
        }
        //3.queries初始化
        int count = sc.nextInt();
        List<List<String>> queries = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<String> part = new ArrayList();
            part.add(sc.next());
            part.add(sc.next());
            queries.add(part);
        }
        //4.开始处理
        double[] res = calcEquation(equations, values, queries);
        for (double val : res) {
            System.out.println(val);
        }
    }

    static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //1.构建有向带权图
        Map<String, List<Pair>> graph = new HashMap<>();
        int index = 0;
        for(List<String> part : equations){
            String from =part.get(0);
            String to = part.get(1);
            double value = values[index++];
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new Pair(to,value));
            graph.computeIfAbsent(to,k -> new ArrayList<>()).add(new Pair(from,1.0 / value));
        }

        //2.开始做dfs
        double[] res = new double[queries.size()];
        index = 0;
        for(List<String> part : queries){
            String from = part.get(0);
            String to = part.get(1);
            if(!graph.containsKey(from) ||  !graph.containsKey(to)){
                res[index++] = -1.0;
                continue;
            }
            double ans = dfs(from,to,from,graph,new HashSet<>(),1.0);
            res[index++] = ans;
        }
        return res;
    }
    static double dfs(String from,String to,String cur, Map<String, List<Pair>> graph,Set<String> existed,double ans){
        if(cur.equals(to)){
            return ans;
        }
        List<Pair> pairs = graph.get(cur);
        for(Pair pair : pairs){
            String key =pair.getKey();
            Double value = pair.getValue();
            if(!existed.contains((key))){
                existed.add(key);
                double res = dfs(from,to,key,graph,existed,ans * value);
                if(res != -1.0){
                    return res;
                }
            }
        }
        return -1.00000;
    }
}
