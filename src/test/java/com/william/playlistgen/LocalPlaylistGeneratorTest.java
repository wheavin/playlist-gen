package com.william.playlistgen;

import org.junit.Before;
import org.junit.Test;

public class LocalPlaylistGeneratorTest extends PlaylistGeneratorTestBase {

    private static boolean libraryIsLoaded = false;
    private LocalPlaylistGenerator objUnderTest;

    @Before
    public void setup() {
        playlistFolder = testFolder.newFolder("PlaylistFolder");
        objUnderTest = new LocalPlaylistGenerator(playlistFolder.getAbsolutePath());
        if (!libraryIsLoaded) {
            objUnderTest.loadLibrary(getMusicLibraryPath());
            libraryIsLoaded = true;
        }
    }

    @Test
    public void generatesGenrePlaylistSuccessfully() {
        objUnderTest.generateGenrePlaylist("Electronic");
        assertPlaylistFileCreated("myElectronicPlaylist.m3u");
    }

    @Test
    public void doesNotGenerateGenrePlaylistForInvalidGenre() {
        objUnderTest.generateGenrePlaylist("Swedish Reggae");
        assertNoPlaylistFileCreated();
    }

    @Test
    public void doesNotGenerateGenrePlaylistForNullGenre() {
        objUnderTest.generateGenrePlaylist(null);
        assertNoPlaylistFileCreated();
    }

    @Test
    public void generatesYearPlaylistSuccessfully() {
        objUnderTest.generateYearPlaylist("1998");
        assertPlaylistFileCreated("my1998Playlist.m3u");
    }

    @Test
    public void doesNotGeneratePlaylistForInvalidYear() {
        objUnderTest.generateYearPlaylist("3004");
        assertNoPlaylistFileCreated();
    }

    @Test
    public void doesNotGeneratePlaylistForNullYear() {
        objUnderTest.generateYearPlaylist(null);
        assertNoPlaylistFileCreated();
    }
}
