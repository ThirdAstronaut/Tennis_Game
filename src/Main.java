public class Main {

    public static void main(String... args) {
        new Controller(new Game(new Player(1, "Server"), new Player(2, "Receiver"))).start();
    }

}
