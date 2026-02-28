package detector;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetector {
    private CascadeClassifier faceCascade;

    public FaceDetector() {
        this.faceCascade = new CascadeClassifier("haarcascade_frontalface_default.xml");
        if (this.faceCascade.empty()) {
            System.out.println("❌ ERRO: XML não encontrado na raiz do projeto!");
        } else {
            System.out.println("✅ Detector de rostos carregado com sucesso.");
        }
    }

    public boolean detectAndDraw(Mat frame) {
        MatOfRect faces = new MatOfRect();


        faceCascade.detectMultiScale(frame, faces, 1.1, 6, 0, new Size(150, 150), new Size());

        Rect[] facesArray = faces.toArray();
        for (Rect rect : facesArray) {
            Imgproc.rectangle(frame, rect.tl(), rect.br(), new Scalar(0, 255, 0), 3);
            Imgproc.putText(frame, "FOCADO", new Point(rect.x, rect.y - 10),
                    Imgproc.FONT_HERSHEY_SIMPLEX, 0.7, new Scalar(0, 255, 0), 2);
        }

        return facesArray.length > 0;
    }
}