package controller;

import detector.FaceDetector;
import service.AttentionService;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import nu.pattern.OpenCV;

public class FocusController {
    private VideoCapture camera;
    private FaceDetector detector;
    private AttentionService service;
    private boolean videoExecutando = false;


    private final String CAMINHO_VIDEO = "/Users/ariele/Desktop/Estudos/projetos/backend/Focus-Monitora/alertateste.mp4";

    public void start() {
        OpenCV.loadShared();
        camera = new VideoCapture(0);
        detector = new FaceDetector();
        service = new AttentionService();

        if (!camera.isOpened()) {
            System.out.println("Erro ao abrir a câmera!");
            return;
        }

        Mat frame = new Mat();
        System.out.println("🚀 Monitoramento iniciado... Pressione ESC na janela da câmera para sair.");

        while (true) {
            if (camera.read(frame)) {
                boolean hasFace = detector.detectAndDraw(frame);
                service.updateDetection(hasFace);

                if (service.isUserDistracted()) {
                    abrirVideoComPlay();
                } else {
                    fecharVideoAutomatico();
                }

                HighGui.imshow("Focus Monitor - Camera", frame);
                if (HighGui.waitKey(30) >= 0) break;
            }
        }

        camera.release();
        HighGui.destroyAllWindows();
        System.exit(0);
    }

    private void abrirVideoComPlay() {
        if (!videoExecutando) {
            try {

                String script = "tell application \"QuickTime Player\"\n" +
                        "   activate\n" +
                        "   open POSIX file \"" + CAMINHO_VIDEO + "\"\n" +
                        "   play document 1\n" +
                        "end tell";

                new ProcessBuilder("osascript", "-e", script).start();
                videoExecutando = true;
                System.out.println("⚠️ ALERTA: Usuário distraído! Vídeo iniciado.");
            } catch (Exception e) {
                System.out.println("Erro ao abrir vídeo: " + e.getMessage());
            }
        }
    }

    private void fecharVideoAutomatico() {
        if (videoExecutando) {
            try {
                new ProcessBuilder("pkill", "-x", "QuickTime Player").start();
                videoExecutando = false;
                System.out.println("✅ FOCO RECUPERADO: Fechando alerta.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}