// TC : O(totalMeetings * logn)
// SC : O(n)

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        PriorityQueue<Integer> availableRooms = new PriorityQueue();
        PriorityQueue<Pair> busyRooms = new PriorityQueue();
        
        for(int room = 0;room<n;room++) availableRooms.add(room);
        
        int rooms[] = new int[n];
        int totalMeetings = meetings.length;
        int idx = 0;
        Arrays.sort(meetings, ((meeting1, meeting2)-> Integer.compare(meeting1[0], meeting2[0])));
        long currTime = 0;
        
        while(idx<totalMeetings){
            int currMeetingStartTime = meetings[idx][0];
            int currMeetingEndTime = meetings[idx][1];
            
            currTime = Math.max(currTime, (long)currMeetingStartTime);
            
            while(busyRooms.size()>0 && busyRooms.peek().endTime <= currTime){
                Pair pair = busyRooms.remove();
                availableRooms.add(pair.roomNo);
            }
            
            if(availableRooms.size() == 0){
                Pair pair = busyRooms.remove();
                currTime = pair.endTime;
                availableRooms.add(pair.roomNo);
            }
            
            int duration = currMeetingEndTime - currMeetingStartTime;
            int roomNo = availableRooms.remove();
            rooms[roomNo]++;
            
            busyRooms.add(new Pair(roomNo, currTime+(long)duration));
            idx++;
        }
        
        int maxMeetingRoom = 0;
        
        for(int room = 0; room<n; room++){
            if(rooms[room] > rooms[maxMeetingRoom]){
                maxMeetingRoom = room;
            }
        }
        
        return maxMeetingRoom;
    }
    
    private class Pair implements Comparable<Pair> {
        private int roomNo;
        private long endTime;
        
        private Pair(int roomNo, long endTime){
            this.roomNo = roomNo;
            this.endTime = endTime;
        }
        
        public int compareTo(Pair other){
            if(this.endTime!=other.endTime){
                if(this.endTime < other.endTime) return -1;
                return 1;
            }
            
            return this.roomNo - other.roomNo;
        }
    }
}