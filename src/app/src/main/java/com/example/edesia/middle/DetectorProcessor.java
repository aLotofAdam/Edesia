package com.example.edesia.middle;

import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

/**
 * A very simple Processor which gets detected TextBlocks and adds them to the overlay
 * as OcrGraphics.
 */
public class DetectorProcessor implements Detector.Processor<TextBlock>{
    private GraphicOverlay<OCRGraphic> graphicOverlay;

    public DetectorProcessor(GraphicOverlay<OCRGraphic> ocrGraphicOverlay){
        graphicOverlay = ocrGraphicOverlay;
    }

    /**
     * Called by the detector to deliver detection results.
     * If your application called for it, this could be a place to check for
     * equivalent detections by tracking TextBlocks that are similar in location and content from
     * previous frames, or reduce noise by eliminating TextBlocks that have not persisted through
     * multiple detections.
     */
    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        graphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {
                Log.d("Processor", "Text detected! " + item.getValue());
                OCRGraphic graphic = new OCRGraphic(graphicOverlay, item);
                graphicOverlay.add(graphic);
            }
        }
    }

    //frees resources used by detection processor
    @Override
    public void release() {
        graphicOverlay.clear();
    }
}
