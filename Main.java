import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public List<Song> getSongs() {
        return songs;
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
    private boolean isPlaying;
    private int currentSongIndex;

    public MusicPlayer() {
        this.library = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.volume = 50.0; // Default volume
        this.isPlaying = false;
        this.currentSongIndex = -1;
    }

    public List<Song> getLibrary() {
        return library;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
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

    public void playSong(int index) {
        if (index < 0 || index >= library.size()) {
            System.out.println("Invalid song index.");
            return;
        }

        if (isPlaying) {
            System.out.println("Song is already playing.");
            return;
        }

        Song song = library.get(index);
        System.out.println("Now playing: " + song.getTitle() + " - " + song.getArtist());
        isPlaying = true;
        currentSongIndex = index;
    }

    public void pauseSong() {
        if (isPlaying) {
            System.out.println("Song paused.");
            isPlaying = false;
        } else {
            System.out.println("No song is currently playing.");
        }
    }

    public void resumeSong() {
        if (!isPlaying && currentSongIndex != -1) {
            Song song = library.get(currentSongIndex);
            System.out.println("Resuming: " + song.getTitle() + " - " + song.getArtist());
            isPlaying = true;
        } else {
            System.out.println("No paused song found.");
        }
    }

    public void stopSong() {
        if (isPlaying) {
            Song song = library.get(currentSongIndex);
            System.out.println("Stopping: " + song.getTitle() + " - " + song.getArtist());
            isPlaying = false;
            currentSongIndex = -1;
        } else {
            System.out.println("No song is currently playing.");
        }
    }

    public void setVolume(double volume) {
        this.volume = volume;
        System.out.println("Volume set to: " + volume);
    }

    public void shufflePlaylist(Playlist playlist) {
        if (playlist == null || playlist.getName().equals("Favourite Songs")) {
            System.out.println("Cannot shuffle the main playlist.");
            return;
        }

        List<Song> songs = playlist.getSongs();
        java.util.Collections.shuffle(songs);
        playlist.displaySongs();
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

        JFrame frame = new JFrame("Music Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel libraryPanel = new JPanel(new BorderLayout());
        JLabel libraryLabel = new JLabel("Music Library");
        libraryLabel.setHorizontalAlignment(JLabel.CENTER);
        libraryPanel.add(libraryLabel, BorderLayout.NORTH);

        JPanel librarySongsPanel = new JPanel(new GridLayout(0, 1));
        for (int i = 0; i < player.getLibrary().size(); i++) {
            Song song = player.getLibrary().get(i);
            JButton songButton = new JButton(song.getTitle() + " - " + song.getArtist());
            int finalI = i;
            songButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player.playSong(finalI);
                }
            });
            librarySongsPanel.add(songButton);
        }
        libraryPanel.add(new JScrollPane(librarySongsPanel), BorderLayout.CENTER);

        JPanel playlistPanel = new JPanel(new BorderLayout());
        JLabel playlistLabel = new JLabel("Playlists");
        playlistLabel.setHorizontalAlignment(JLabel.CENTER);
        playlistPanel.add(playlistLabel, BorderLayout.NORTH);

        JPanel playlistButtonsPanel = new JPanel(new GridLayout(0, 1));
        for (int i = 0; i < player.getPlaylists().size(); i++) {
            Playlist playlist = player.getPlaylists().get(i);
            JButton playlistButton = new JButton(playlist.getName());
            int finalI = i;
            playlistButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    player.shufflePlaylist(playlist);
                }
            });
            playlistButtonsPanel.add(playlistButton);
        }

        playlistPanel.add(new JScrollPane(playlistButtonsPanel), BorderLayout.CENTER);

        frame.add(libraryPanel, BorderLayout.CENTER);
        frame.add(playlistPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }
}
