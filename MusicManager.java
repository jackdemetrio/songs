import java.util.Scanner;

public class MusicManager {
    private DoublyLinkedList playlist;

    public MusicManager() {
        this.playlist = new DoublyLinkedList();
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\nBem-vindo ao seu Gerenciador de Músicas!");
            System.out.println("1. Próxima música");
            System.out.println("2. Música anterior");
            System.out.println("3. Ordenar playlist");
            System.out.println("4. Tocar música");
            System.out.println("5. Adicionar música");
            System.out.println("6. Remover música");
            System.out.println("7. Listar músicas");
            System.out.println("8. Sair");
            System.out.print("Digite a opção desejada: ");
            option = scanner.nextInt();
            scanner.nextLine(); // consumindo a nova linha

            switch (option) {
                case 1:
                    playlist.next();
                    break;
                case 2:
                    playlist.previous();
                    break;
                case 3:
                    sortPlaylist(scanner);
                    break;
                case 4:
                    playCurrentMusic();
                    break;
                case 5:
                    addMusic(scanner);
                    break;
                case 6:
                    removeMusic(scanner);
                    break;
                case 7:
                    playlist.listMusic();
                    break;
                case 8:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (option != 8);
        scanner.close();
    }

    private void sortPlaylist(Scanner scanner) {
        System.out.println("Escolha o critério de ordenação:");
        System.out.println("1. Título");
        System.out.println("2. Artista");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consumindo a nova linha
        if (choice == 1) {
            playlist.sortByTitle();
        } else if (choice == 2) {
            playlist.sortByArtist();
        } else {
            System.out.println("Critério inválido!");
        }
    }

    private void playCurrentMusic() {
        if (playlist.current != null) {
            System.out.println("Tocando: " + playlist.current.music);
        } else {
            System.out.println("Nenhuma música está sendo tocada no momento.");
        }
    }

    private void addMusic(Scanner scanner) {
        System.out.print("Título da música: ");
        String title = scanner.nextLine();
        System.out.print("Artista: ");
        String artist = scanner.nextLine();
        System.out.print("Álbum: ");
        String album = scanner.nextLine();
        System.out.print("Duração (segundos): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // consumindo a nova linha

        Music music = new Music(title, artist, album, duration);
        System.out.println("\nDeseja adicionar a música:");
        System.out.println("1. No início");
        System.out.println("2. No fim");
        System.out.println("3. Em uma posição específica");
        int positionChoice = scanner.nextInt();
        scanner.nextLine(); // consumindo a nova linha
        if (positionChoice == 1) {
            playlist.addMusicAtStart(music);
        } else if (positionChoice == 2) {
            playlist.addMusicAtEnd(music);
        } else if (positionChoice == 3) {
            System.out.print("Digite a posição: ");
            int position = scanner.nextInt();
            scanner.nextLine(); // consumindo a nova linha
            playlist.addMusicAtPosition(music, position);
        } else {
            System.out.println("Opção inválida! Adicionando ao final por padrão.");
            playlist.addMusicAtEnd(music);
        }
        System.out.println("Música adicionada!");
    }

    private void removeMusic(Scanner scanner) {
        System.out.print("Título da música a ser removida: ");
        String title = scanner.nextLine();
        playlist.removeMusic(title);
        System.out.println("Música removida (se encontrada)!");
    }

    public static void main(String[] args) {
        MusicManager manager = new MusicManager();
        manager.showMenu();
    }
}
