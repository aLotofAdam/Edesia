package com.example.edesia.middle;

import android.util.Log;
import android.util.SparseArray;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

import java.util.ArrayList;
import java.util.List;

/**
 * A very simple Processor which gets detected TextBlocks and adds them to the overlay
 * as OcrGraphics.
 */
public class DetectorProcessor implements Detector.Processor<TextBlock>{
    private GraphicOverlay<OCRGraphic> graphicOverlay;
    List list = new ArrayList();

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
                final String worst = item.getValue();
                if(worst.length() > 1 && Character.isUpperCase(worst.charAt(0)) && worst
                        .matches("[A-Za-z-]+") && worst.charAt(worst.length() - 1) != '-') {
                    Log.d("Processor", "Text detected! " + worst);
                    OCRGraphic graphic = new OCRGraphic(graphicOverlay, item);
                    graphicOverlay.add(graphic);
                    list.add(item);
                }
            }
        }
    }

    //frees resources used by detection processor
    @Override
    public void release() {
        graphicOverlay.clear();
    }
}
