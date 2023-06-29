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
        System.out.println("Welcome to the Music Player by Saurabh Gaikwad ©2023");
        MusicPlayer player = new MusicPlayer();
        Song song1 = new Song("5% Tint", "Travis Scott", "Astroworld", 3.5);
        Song song2 = new Song("Out Of Time", "The Weeknd", "Dawn FM", 4.2);
        Song song3 = new Song("Alizeh", "Arijit Singh", "Ae Dil Hai Mushkil", 2.8);
        Song song4 = new Song("More & More", "Twice", "Single", 3.8);
        Song song5 = new Song("Metamodernity", "Vansire the band", "Reflection No.5", 3.7);
        player.importSong(song1);
        player.importSong(song2);
        player.importSong(song3);
        player.importSong(song4);
        player.importSong(song5);

        Playlist playlist1 = new Playlist("Favourite Songs");
        player.createPlaylist(playlist1.getName());
        player.addSongToPlaylist(song1, playlist1);
        player.addSongToPlaylist(song2, playlist1);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("______________________________________________________________");
            System.out.println("1. Display Library");
            System.out.println("2. Display Playlists");
            System.out.println("3. Play Song");
            System.out.println("4. Pause Song");
            System.out.println("5. Stop Song");
            System.out.println("6. Set Volume");
            System.out.println("7. Add Song to Library");
            System.out.println("8. Create Playlist");
            System.out.println("9. Add Song to Playlist");
            System.out.println("10. Remove Song from Playlist");
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
                case 7:
                    System.out.print("Enter the song title: ");
                    scanner.nextLine(); // Consume the newline character
                    String newSongTitle = scanner.nextLine();
                    System.out.print("Enter the artist name: ");
                    String newSongArtist = scanner.nextLine();
                    System.out.print("Enter the album name: ");
                    String newSongAlbum = scanner.nextLine();
                    System.out.print("Enter the duration: ");
                    double newSongDuration = scanner.nextDouble();
                    Song newSong = new Song(newSongTitle, newSongArtist, newSongAlbum, newSongDuration);
                    player.importSong(newSong);
                    System.out.println("Song added to the library.");
                    break;
                case 8:
                    System.out.print("Enter the playlist name: ");
                    scanner.nextLine(); // Consume the newline character
                    String playlistName = scanner.nextLine();
                    player.createPlaylist(playlistName);
                    System.out.println("Playlist created.");
                    break;
                case 9:
                    player.displayLibrary();
                    System.out.print("Enter the song number to add to the playlist: ");
                    int songIndex = scanner.nextInt();
                    player.displayPlaylists();
                    System.out.print("Enter the playlist number to add the song: ");
                    int playlistIndex = scanner.nextInt();
                    Song songToAdd = player.getLibrary().get(songIndex - 1);
                    Playlist playlistToAdd = player.getPlaylists().get(playlistIndex - 1);
                    player.addSongToPlaylist(songToAdd, playlistToAdd);
                    System.out.println("Song added to the playlist.");
                    break;
                case 10:
                    player.displayPlaylists();
                    System.out.print("Enter the playlist number to remove the song: ");
                    int playlistToRemoveIndex = scanner.nextInt();
                    Playlist playlistToRemove = player.getPlaylists().get(playlistToRemoveIndex - 1);
                    playlistToRemove.displaySongs();
                    System.out.print("Enter the song number to remove from the playlist: ");
                    int songToRemoveIndex = scanner.nextInt();
                    Song songToRemove = playlistToRemove.getSongs().get(songToRemoveIndex - 1);
                    player.removeSongFromPlaylist(songToRemove, playlistToRemove);
                    System.out.println("Song removed from the playlist.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
