// TC: O(uniqueIds*log(uniqueIds))
// SC: O(uniqueIds)

class Solution {
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        int reports = report.length;
        
        HashMap<Integer, Long> map = new HashMap();
        
        HashSet<String> positive_feedback_set = new HashSet();
        for(String feedback : positive_feedback) positive_feedback_set.add(feedback);
        
        HashSet<String> negative_feedback_set = new HashSet();
        for(String feedback : negative_feedback) negative_feedback_set.add(feedback);
        
        for(int idx =0; idx<reports; idx++){
            String currReport = report[idx];
            int id = student_id[idx];
            
            String split[] = currReport.split(" ");
            long score = 0;
            
            for(String word : split){
                if(positive_feedback_set.contains(word)) score+=3l;
                else if(negative_feedback_set.contains(word)) score-=1l;
            }
            
            map.put(id, score);
        }
        
        PriorityQueue<Integer> heap;
        heap = new PriorityQueue<Integer>((student1, student2)->((((long)map.get(student1)) == ((long)map.get(student2)))?Integer.compare(student2, student1): Long.compare((long)map.get(student1), (long)map.get(student2))));
        
        for(int id : map.keySet()){
            heap.add(id);
            if(heap.size()>k) heap.remove();
        }
        
        List<Integer> ans = new ArrayList();
        while(heap.size()>0) ans.add(heap.remove());
        
        Collections.reverse(ans);
        
        return ans;
    }
}