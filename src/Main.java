import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Song {
    private String title;
    private String artist;
    private String album;
    private double duration;

    public Song(String title, String artist, String album, double duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public double getDuration() {
        return duration;
    }
}

class Playlist {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public void displaySongs() {
        if (songs.isEmpty()) {
            System.out.println("Playlist is empty.");
        } else {
            System.out.println("Playlist: " + name);
            for (int i = 0; i < songs.size(); i++) {
                Song song = songs.get(i);
                System.out.println((i + 1) + ". " + song.getTitle() + " - " + song.getArtist());
            }
        }
    }
}

class MusicPlayer {
    private List<Song> library;
    private List<Playlist> playlists;
    private double volume;

    public List<Song> getLibrary() {
        return library;
    }


    public MusicPlayer() {
        this.library = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.volume = 50.0; // Default volume
    }

    public void importSong(Song song) {
        library.add(song);
    }

    public void createPlaylist(String name) {
        Playlist playlist = new Playlist(name);
        playlists.add(playlist);
    }

    public void addSongToPlaylist(Song song, Playlist playlist) {
        playlist.addSong(song);
    }

    public void removeSongFromPlaylist(Song song, Playlist playlist) {
        playlist.removeSong(song);
    }

    public void displayLibrary() {
        System.out.println("Music Library:");
        for (int i = 0; i < library.size(); i++) {
            Song song = library.get(i);
            System.out.println((i + 1) + ". " + song.getTitle() + " - " + song.getArtist());
        }
    }

    public void displayPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("No playlists found.");
        } else {
            System.out.println("Playlists:");
            for (int i = 0; i < playlists.size(); i++) {
                Playlist playlist = playlists.get(i);
                System.out.println((i + 1) + ". " + playlist.getName());
            }
        }
    }

    public void playSong(Song song) {
        System.out.println("Now playing: " + song.getTitle() + " - " + song.getArtist());
    }

    public void pauseSong() {
        System.out.println("Song paused.");
    }

    public void stopSong() {
        System.out.println("Song stopped.");
    }

    public void setVolume(double volume) {
        this.volume = volume;
        System.out.println("Volume set to: " + volume);
    }
}

public class Main {
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        Song song1 = new Song("Song 1", "Artist 1", "Album 1", 3.5);
        Song song2 = new Song("Song 2", "Artist 2", "Album 2", 4.2);
        Song song3 = new Song("Song 3", "Artist 3", "Album 3", 2.8);
        player.importSong(song1);
        player.importSong(song2);
        player.importSong(song3);

        Playlist playlist1 = new Playlist("My Playlist");
        player.createPlaylist(playlist1.getName());
        player.addSongToPlaylist(song1, playlist1);
        player.addSongToPlaylist(song2, playlist1);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("1. Display Library");
            System.out.println("2. Display Playlists");
            System.out.println("3. Play Song");
            System.out.println("4. Pause Song");
            System.out.println("5. Stop Song");
            System.out.println("6. Set Volume");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    player.displayLibrary();
                    break;
                case 2:
                    player.displayPlaylists();
                    break;
                case 3:
                    player.displayLibrary();
                    System.out.print("Enter the song number to play: ");
                    int songNumber = scanner.nextInt();
                    Song song = player.getLibrary().get(songNumber - 1);
                    player.playSong(song);
                    break;
                case 4:
                    player.pauseSong();
                    break;
                case 5:
                    player.stopSong();
                    break;
                case 6:
                    System.out.print("Enter the volume (0-100): ");
                    double volume = scanner.nextDouble();
                    player.setVolume(volume);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
