public class Main {
    public static void main(String[] args) {
        ArrayDequeCustom<String> array = new ArrayDequeCustom<String>();
        String t = "dssf";
        array.addFirst(t);
        System.out.println(array.getFirst());
        LinkedListCustom<String> list = new LinkedListCustom<String>();
        list.add("qwewq");
        list.add("eeee");
        list.add("lol");
        System.out.println(list.contains("lol"));
        Object[] arra = list.toArray();
        list.clear();
        for(int i =0 ; i < list.size(); i ++){
            System.out.println(arra[i]);
        }
    }
}
