public class unionfind {
    // TODO: Instance variables
    private int[] parent;
    private int[] rank;
    private int size;
    private int[] sizeOf;
    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public unionfind(int N) {
        // TODO: YOUR CODE HERE
        int[] parent = new int[N];
        int[] sizeOf = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
            this.size = i;
        }
        for (int i = 0; i < N; i++) {
            sizeOf[i] = 1;
        }
        this.parent = parent;
        this.rank = rank;
        this.size +=1;
        this.sizeOf = sizeOf;
    }
    public void print(){
        for(int i = 0; i < parent.length; i++){
            System.out.print(parent[i] + " ");
        }
    }
    /* Returns the size of the set V belongs to. */
    

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return parent[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (find(v1)==find(v2)){
            while (parent(v2) != find(v2)){
                parent[v2] = find(v2);
            }
            while (parent(v1) !=find(v2)) { 
                parent[v1] = find(v1);
            }
            return true;
        }
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (parent[v] < 0 ){
            return v;
        }
        return find(parent[v]);
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (sizeOf[find(v2)] > sizeOf[find(v1)]){
            sizeOf[find(v2)] += sizeOf[find(v1)];
            parent[find(v1)] = find(v2);
            parent[find(v2)] = sizeOf[find(v2)];
        } else {
            sizeOf[find(v1)] += sizeOf[find(v2)];
            parent[find(v2)] = find(v1);
            // parent[v1] = sizeOf(v1);
            parent[find(v1)] = -sizeOf[find(v1)];
        }
    }
    public static void main(String[] args) {
        unionfind uf = new unionfind(8);
        uf.union(0, 1);
        uf.union(0, 6);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(5, 7);
        uf.union(1, 3);
        uf.union(3, 7);
        uf.connected(3, 7);
        uf.print();
    }
}