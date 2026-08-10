import java.util.*;

public class MovieRentingSystem {
    static class Stock implements Comparable<Stock>{
        final int movie;
        final int shop;
        final int price;

        Stock(int movie, int shop, int price) {
            this.movie = movie;
            this.shop = shop;
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Stock stock = (Stock) o;
            return movie == stock.movie && shop == stock.shop && price == stock.price;
        }

        @Override
        public int hashCode() {
            return Objects.hash(movie, shop, price);
        }

        @Override
        public int compareTo(Stock o) {
            int compare = Integer.compare(this.price, o.price);
            if (compare != 0) return compare;
            compare =  Integer.compare(this.shop, o.shop);
            if (compare != 0) return compare;
            return Integer.compare(this.movie, o.movie);
        }
    }

    Map<Integer, Stock> keyToStock;
    Map<Stock, Boolean> isAvailable;
    PriorityQueue<Stock> currRented; // report
    Map<Integer, PriorityQueue<Stock>> movieToStore;

    public MovieRentingSystem(int n, int[][] entries) {
        keyToStock = new HashMap<>();
        movieToStore = new HashMap<>(n);
        currRented = new PriorityQueue<>();
        isAvailable = new HashMap<>();
        for (int i = 0; i < entries.length; i++) {
            int movie = entries[i][1];
            int shop = entries[i][0];
            int price = entries[i][2];
            if (!movieToStore.containsKey(movie)) {
                movieToStore.put(movie, new PriorityQueue<>());
            }
            Stock newStock = new Stock(movie, shop, price);
            movieToStore.get(movie).add(newStock);
            isAvailable.put(newStock, true);
            keyToStock.put(key(shop, movie), newStock);
        }
    }

    public int key(int shop, int movie) {
        return movie * 10000 + shop;
    }

    public List<Integer> search(int movie) {
        List<Integer> result = new LinkedList<>();
        List<Stock> addBack = new LinkedList<>();
        PriorityQueue<Stock> pq = movieToStore.get(movie);
        if (pq == null) {
            return result;
        }
        int count = 0;
        while (!pq.isEmpty() && count < 5) {
            Stock stock = pq.poll();
            if (!isAvailable.get(stock)) {
                continue;
            }
            addBack.add(stock);
            result.add(stock.shop);
            count++;
        }
        pq.addAll(addBack);
        return result;
    }

    public void rent(int shop, int movie) {
        int stock = key(shop, movie);
        Stock newStock = keyToStock.get(stock);
        currRented.add(newStock);
        isAvailable.put(newStock, false);
    }

    public void drop(int shop, int movie) {
        int stock = key(shop, movie);
        Stock newStock = keyToStock.get(stock);
        isAvailable.put(newStock, true);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> result = new LinkedList<>();
        List<Stock> addBack = new LinkedList<>();
        int count = 0;
        while (!currRented.isEmpty() && count < 5) {
            Stock stock = currRented.poll();
            if (isAvailable.get(stock)) {
                continue;
            }
            addBack.add(stock);
            List<Integer> list = new LinkedList<>();
            list.add(stock.shop);
            list.add(stock.movie);
            result.addLast(list);
            count++;
        }
        currRented.addAll(addBack);
        return result;
    }

    public static void main(String[] args) {
        MovieRentingSystem mvs = new MovieRentingSystem(3, new int[][]{{0, 1, 5}, {0, 2, 6}, {0, 3, 7}, {1, 1, 4}, {1, 2, 7}, {2, 1, 5}});
        System.out.println(mvs.search(1));
        mvs.rent(0,1);
        mvs.rent(1,2);
        System.out.println(mvs.report());
        mvs.drop(1,2);
        System.out.println(mvs.search(2));
    }
}
