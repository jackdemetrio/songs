import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    Node current;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.current = null;
    }

    public void addMusicAtEnd(Music music) {
        Node newNode = new Node(music);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void addMusicAtStart(Music music) {
        Node newNode = new Node(music);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public void addMusicAtPosition(Music music, int position) {
        Node newNode = new Node(music);
        if (position == 0) {
            addMusicAtStart(music);
            return;
        }
        Node temp = head;
        for (int i = 0; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null) {
            addMusicAtEnd(music);
        } else {
            newNode.next = temp.next;
            newNode.prev = temp;
            temp.next.prev = newNode;
            temp.next = newNode;
        }
    }

    public void removeMusic(String title) {
        Node temp = head;
        while (temp != null && !temp.music.getTitle().equals(title)) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Música não encontrada!");
            return;
        }
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        } else {
            head = temp.next;
        }
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        } else {
            tail = temp.prev;
        }
    }

    public void listMusic() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.music);
            temp = temp.next;
        }
    }

    public void next() {
        if (current == null) {
            current = head;
        } else {
            current = current.next;
        }
        if (current != null) {
            System.out.println("Tocando: " + current.music);
        } else {
            System.out.println("Fim da playlist.");
        }
    }

    public void previous() {
        if (current == null) {
            current = tail;
        } else {
            current = current.prev;
        }
        if (current != null) {
            System.out.println("Tocando: " + current.music);
        } else {
            System.out.println("Início da playlist.");
        }
    }

    public void sortByTitle() {
        LinkedList<Music> list = new LinkedList<>();
        Node temp = head;
        while (temp != null) {
            list.add(temp.music);
            temp = temp.next;
        }
        Collections.sort(list, Comparator.comparing(Music::getTitle));
        head = tail = null;
        for (Music music : list) {
            addMusicAtEnd(music);
        }
    }

    public void sortByArtist() {
        LinkedList<Music> list = new LinkedList<>();
        Node temp = head;
        while (temp != null) {
            list.add(temp.music);
            temp = temp.next;
        }
        Collections.sort(list, Comparator.comparing(Music::getArtist));
        head = tail = null;
        for (Music music : list) {
            addMusicAtEnd(music);
        }
    }
}
