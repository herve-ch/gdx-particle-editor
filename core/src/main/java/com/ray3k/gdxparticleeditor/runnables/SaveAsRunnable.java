package com.ray3k.gdxparticleeditor.runnables;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.ray3k.gdxparticleeditor.FileDialogs;

import static com.ray3k.gdxparticleeditor.Core.*;
import static com.ray3k.gdxparticleeditor.Settings.*;

public class SaveAsRunnable implements Runnable {

    private static boolean open;
    private SaveRunnable saveRunnable;

    public void setSaveRunnable(SaveRunnable runnable) {
        saveRunnable = runnable;
    }

    @Override
    public void run() {
        if (open) return;

        //ExecutorService service = Executors.newSingleThreadExecutor();
        //service.execute(() -> {
        open = true;
        stage.getRoot().setTouchable(Touchable.disabled);

        var useFileExtension = preferences.getBoolean(NAME_PRESUME_FILE_EXTENSION, DEFAULT_PRESUME_FILE_EXTENSION);
        var filterPatterns = useFileExtension ? new String[]{"p"} : null;
        var saveHandle = FileDialogs.saveDialog("Save", getDefaultSavePath(), defaultFileName, filterPatterns, "Particle Files (*.p)");

        if (saveHandle != null) {
            openFileFileHandle = saveHandle;
            saveRunnable.run();
        }

        stage.getRoot().setTouchable(Touchable.enabled);
        open = false;
        //});
        //service.shutdown();
    }
}
