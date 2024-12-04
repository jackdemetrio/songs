class Node {
    Music music;
    Node prev;
    Node next;

    Node(Music music) {
        this.music = music;
        this.prev = null;
        this.next = null;
    }
}
