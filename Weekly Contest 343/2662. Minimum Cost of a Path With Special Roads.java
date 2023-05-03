/*
 -> When going from one node to another it is useless to travel to intermediate nodes,
    since final cost remains same (Travelling from node1 to node2 via node3 and node1 to node2 directly yield same cost
    via non special roads)
    
 -> Construct graph of nodes in special roads, and start and target node (since travelling to nodes via non-special roads is redundant)
 
 -> For multiple edges between two nodes we consider edge with minimal weight
 
 -> Shortest path problem for positive edge weights: Dijkstra or Floyd Warshall
 

 Solution-1: Dijkstra
 TC: O(n*n*log(n))
 SC: O(n*n)
 where n = unique points
*/

class Solution {
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        Set<List<Integer>> points = new HashSet();
        List<Integer> startPoint = getCoords(start);
        List<Integer> targetPoint = getCoords(target);
        points.add(startPoint);
        points.add(targetPoint);
        
        HashMap<List<Integer>, Integer> edgeWeightMap = new HashMap();
        
        for(int[] specialRoad: specialRoads){
            int x1 = specialRoad[0];
            int y1 = specialRoad[1];
            int x2 = specialRoad[2];
            int y2 = specialRoad[3];
            int cost = specialRoad[4];
            
            points.add(getCoords(new int[]{x1, y1}));
            points.add(getCoords(new int[]{x2, y2}));
            
            List<Integer> edge = getCoords(new int[]{x1, y1, x2, y2});
            edgeWeightMap.put(edge, Math.min(edgeWeightMap.getOrDefault(edge, Integer.MAX_VALUE), cost));
        }
        
        Set<List<Integer>> visited = new HashSet();
        PriorityQueue<Node> minHeap = new PriorityQueue();
        minHeap.add(new Node(startPoint, 0));
        
        while(minHeap.size()>0){
            Node node = minHeap.remove();
            List<Integer> currPoint = node.point;
            int cost = node.cost;
            
            if(equal(currPoint, targetPoint)) return cost;
            if(visited.contains(currPoint)) continue;
            
            visited.add(currPoint);
            
            for(List<Integer> point: points){
                if(!visited.contains(point)){
                    List<Integer> edge = getCoords(new int[]{currPoint.get(0), currPoint.get(1), point.get(0), point.get(1)});
                    
                    int minEdgeWeight = Math.abs(currPoint.get(0)-point.get(0)) + Math.abs(currPoint.get(1)-point.get(1));
                    if(edgeWeightMap.containsKey(edge)) minEdgeWeight = Math.min(minEdgeWeight, edgeWeightMap.get(edge));
                    
                    minHeap.add(new Node(point, cost+minEdgeWeight));
                }
            }
        }
        
        return -1;
    }
    
    private List<Integer> getCoords(int[] arr){
        List<Integer> coords = new ArrayList();
        
        for(int index=0; index<arr.length; index++) coords.add(arr[index]);
        
        return coords;
    }
    
    private boolean equal(List<Integer> point1, List<Integer> point2){
        return (point1.get(0)==point2.get(0)) && (point1.get(1)==point2.get(1));
    }
    
    private class Node implements Comparable<Node>{
        List<Integer> point;
        int cost;
        
        Node(){}
        
        Node(List<Integer> point, int cost){
            this.point = point;
            this.cost = cost;
        }
        
        public int compareTo(Node other){
            return Integer.compare(this.cost, other.cost);
        }
    }
}


//-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----X-----   


// Solution-2: Coordinate Compression + Floyd Warshall
// TC: O(n^3)
// SC: O(n^2)

// where n = number of unique points

class Solution {
    public int minimumCost(int[] init, int[] target, int[][] specialRoads) {
        Set<List<Integer>> points = new HashSet();
        List<Integer> startPoint = getCoords(init);
        List<Integer> targetPoint = getCoords(target);
        points.add(startPoint);
        points.add(targetPoint);
        
        HashMap<List<Integer>, Integer> edgeWeightMap = new HashMap();
        
        for(int[] specialRoad: specialRoads){
            int x1 = specialRoad[0];
            int y1 = specialRoad[1];
            int x2 = specialRoad[2];
            int y2 = specialRoad[3];
            int cost = specialRoad[4];
            
            points.add(getCoords(new int[]{x1, y1}));
            points.add(getCoords(new int[]{x2, y2}));
            
            List<Integer> edge = getCoords(new int[]{x1, y1, x2, y2});
            edgeWeightMap.put(edge, Math.min(edgeWeightMap.getOrDefault(edge, Integer.MAX_VALUE), cost));
        }
        
        int id = 0;
        HashMap<List<Integer>, Integer> pointToIdMap = new HashMap();
        
        for(List<Integer> point: points){
            pointToIdMap.put(point, id);
            id++;
        }
        
        int nodes = id;
        int[][] dist = new int[nodes][nodes];
        for(int index=0; index<nodes; index++) Arrays.fill(dist[index], (int)1e9);
        
        for(List<Integer> point1: points){
            for(List<Integer> point2: points){
                int id1 = pointToIdMap.get(point1);
                int id2 = pointToIdMap.get(point2);
                
                dist[id1][id2] = Math.abs(point1.get(0)-point2.get(0)) + Math.abs(point1.get(1)-point2.get(1));
            }
        }
        
        for(List<Integer> edge: edgeWeightMap.keySet()){
            int edgeWeight = edgeWeightMap.get(edge);
            
            List<Integer> point1 = new ArrayList();
            point1.add(edge.get(0));
            point1.add(edge.get(1));
            
            List<Integer> point2 = new ArrayList();
            point2.add(edge.get(2));
            point2.add(edge.get(3));
            
            int id1 = pointToIdMap.get(point1);
            int id2 = pointToIdMap.get(point2);
            
            dist[id1][id2] = Math.min(dist[id1][id2], edgeWeight);
        }
        
        for(int node=0; node<nodes; node++){
            for(int start=0; start<nodes; start++){
                for(int end=0; end<nodes; end++){
                    dist[start][end] = Math.min(dist[start][end], dist[start][node]+dist[node][end]);
                }
            }
        }
        
        return dist[pointToIdMap.get(startPoint)][pointToIdMap.get(targetPoint)];
    }
    
    private List<Integer> getCoords(int[] arr){
        List<Integer> coords = new ArrayList();
        
        for(int index=0; index<arr.length; index++) coords.add(arr[index]);
        
        return coords;
    }
}