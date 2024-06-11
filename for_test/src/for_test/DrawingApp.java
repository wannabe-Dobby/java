package for_test;

// 자유롭게 그려지고 색은 바꾼 전/후가 다른 버전

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class DrawingApp extends JFrame {
    private static Color currentColor = Color.BLACK;
    private static String currentMode = "Line";
    private DrawPanel drawPanel;

    public DrawingApp() {
        setTitle("2-2 Drawing Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }

    public static void setColor() {
        Color selectedColor = JColorChooser.showDialog(null, "색 선택", currentColor);
        if (selectedColor != null) {
            currentColor = selectedColor;
        }
    }

    public static void setMode(String mode) {
        currentMode = mode;
    }

    class DrawPanel extends JPanel {
        private ArrayList<ColoredShape> shapes = new ArrayList<>();
        private ArrayList<Point> freeLinePoints = new ArrayList<>();
        private Point startPoint, endPoint;

        public DrawPanel() {
            setBackground(Color.WHITE);
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    startPoint = e.getPoint();
                    if (currentMode.equals("FreeLine")) {
                        freeLinePoints.clear();
                        freeLinePoints.add(startPoint);
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    endPoint = e.getPoint();
                    if (currentMode.equals("FreeLine")) {
                        freeLinePoints.add(endPoint);
                        addShape(freeLinePoints);
                    } else if (currentMode.equals("Text")) {
                        String text = JOptionPane.showInputDialog("Enter text:");
                        if (text != null && !text.isEmpty()) {
                            shapes.add(new ColoredShape(new TextShape(startPoint, text), currentColor));
                        }
                    } else {
                        addShape(startPoint, endPoint);
                    }
                    repaint();
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    endPoint = e.getPoint();
                    if (currentMode.equals("FreeLine")) {
                        freeLinePoints.add(endPoint);
                        addShape(freeLinePoints);
                    }
                    repaint();
                }
            });
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            for (ColoredShape coloredShape : shapes) {
                g2.setColor(coloredShape.getColor());
                if (coloredShape.getShape() instanceof TextShape) {
                    ((TextShape) coloredShape.getShape()).draw(g2);
                } else {
                    g2.draw(coloredShape.getShape());
                }
            }

            if (startPoint != null && endPoint != null && !currentMode.equals("FreeLine") && !currentMode.equals("Text")) {
                g2.setColor(currentColor);
                Shape shape = getShape(startPoint, endPoint);
                g2.draw(shape);
            }
        }

        private void addShape(Point start, Point end) {
            shapes.add(new ColoredShape(getShape(start, end), currentColor));
        }

        private void addShape(ArrayList<Point> points) {
            Path2D path = new Path2D.Float();
            if (points.size() > 1) {
                Point start = points.get(0);
                path.moveTo(start.x, start.y);
                for (Point point : points) {
                    path.lineTo(point.x, point.y);
                }
            }
            shapes.add(new ColoredShape(path, currentColor));
        }

        private Shape getShape(Point start, Point end) {
            switch (currentMode) {
                case "Line":
                    return new Line2D.Float(start, end);
                case "Rect":
                    return new Rectangle(Math.min(start.x, end.x), Math.min(start.y, end.y),
                            Math.abs(start.x - end.x), Math.abs(start.y - end.y));
                case "Oval":
                    return new Ellipse2D.Float(Math.min(start.x, end.x), Math.min(start.y, end.y),
                            Math.abs(start.x - end.x), Math.abs(start.y - end.y));
                case "RndRect":
                    return new RoundRectangle2D.Float(Math.min(start.x, end.x), Math.min(start.y, end.y),
                            Math.abs(start.x - end.x), Math.abs(start.y - end.y), 20, 20);
                case "Text":
                    return new TextShape(start, "Text");
                default:
                    return null;
            }
        }
    }

    class TextShape extends Rectangle {
        private String text;

        public TextShape(Point start, String text) {
            super(start.x, start.y, 100, 30); // 텍스트 영역의 크기
            this.text = text;
        }

        public void draw(Graphics2D g2) {
            g2.setColor(currentColor);
            g2.draw(this);
            g2.drawString(text, x + 5, y + 20);
        }
    }

    class ColoredShape {
        private Shape shape;
        private Color color;

        public ColoredShape(Shape shape, Color color) {
            this.shape = shape;
            this.color = color;
        }

        public Shape getShape() {
            return shape;
        }

        public Color getColor() {
            return color;
        }
    }
}





/*


// 자유롭게 그려지고 색도 같이 바뀌는 버전


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class DrawingApp extends JFrame {
    private static Color currentColor = Color.BLACK;
    private static String currentMode = "Line";
    private DrawPanel drawPanel;

    public DrawingApp() {
        setTitle("2-2 Drawing Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }

    public static void setColor() {
        Color selectedColor = JColorChooser.showDialog(null, "색 선택", currentColor);
        if (selectedColor != null) {
            currentColor = selectedColor;
        }
    }

    public static void setMode(String mode) {
        currentMode = mode;
    }

    class DrawPanel extends JPanel {
        private ArrayList<Shape> shapes = new ArrayList<>();
        private ArrayList<Point> freeLinePoints = new ArrayList<>();
        private Point startPoint, endPoint;

        public DrawPanel() {
            setBackground(Color.WHITE);
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    startPoint = e.getPoint();
                    if (currentMode.equals("FreeLine")) {
                        freeLinePoints.clear();
                        freeLinePoints.add(startPoint);
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    endPoint = e.getPoint();
                    if (currentMode.equals("FreeLine")) {
                        freeLinePoints.add(endPoint);
                        addShape(freeLinePoints);
                    } else if (currentMode.equals("Text")) {
                        String text = JOptionPane.showInputDialog("Enter text:");
                        if (text != null && !text.isEmpty()) {
                            shapes.add(new TextShape(startPoint, text));
                        }
                    } else {
                        addShape(startPoint, endPoint);
                    }
                    repaint();
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    endPoint = e.getPoint();
                    if (currentMode.equals("FreeLine")) {
                        freeLinePoints.add(endPoint);
                        addShape(freeLinePoints);
                    }
                    repaint();
                }
            });
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(currentColor);

            for (Shape shape : shapes) {
                if (shape instanceof TextShape) {
                    ((TextShape) shape).draw(g2);
                } else {
                    g2.draw(shape);
                }
            }

            if (startPoint != null && endPoint != null && !currentMode.equals("FreeLine") && !currentMode.equals("Text")) {
                g2.setColor(currentColor);
                Shape shape = getShape(startPoint, endPoint);
                g2.draw(shape);
            }
        }

        private void addShape(Point start, Point end) {
            shapes.add(getShape(start, end));
        }

        private void addShape(ArrayList<Point> points) {
            Path2D path = new Path2D.Float();
            if (points.size() > 1) {
                Point start = points.get(0);
                path.moveTo(start.x, start.y);
                for (Point point : points) {
                    path.lineTo(point.x, point.y);
                }
            }
            shapes.add(path);
        }

        private Shape getShape(Point start, Point end) {
            switch (currentMode) {
                case "Line":
                    return new Line2D.Float(start, end);
                case "Rect":
                    return new Rectangle(Math.min(start.x, end.x), Math.min(start.y, end.y),
                            Math.abs(start.x - end.x), Math.abs(start.y - end.y));
                case "Oval":
                    return new Ellipse2D.Float(Math.min(start.x, end.x), Math.min(start.y, end.y),
                            Math.abs(start.x - end.x), Math.abs(start.y - end.y));
                case "RndRect":
                    return new RoundRectangle2D.Float(Math.min(start.x, end.x), Math.min(start.y, end.y),
                            Math.abs(start.x - end.x), Math.abs(start.y - end.y), 20, 20);
                case "Text":
                    return new TextShape(start, "Text");
                default:
                    return null;
            }
        }
    }

    class TextShape extends Rectangle {
        private String text;

        public TextShape(Point start, String text) {
            super(start.x, start.y, 100, 30); // 텍스트 영역의 크기
            this.text = text;
        }

        public void draw(Graphics2D g2) {
            g2.setColor(currentColor);
            g2.draw(this);
            g2.drawString(text, x + 5, y + 20);
        }
    }
}

*/