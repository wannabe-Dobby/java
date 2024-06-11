package for_test;

// 텍스트 입력 받아 입력할 수 있는 버전 (텍스트 겉에 틀 없음 / 다른 버전들은 틀 있음)

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingApp extends JFrame {
    private DrawingPanel drawingPanel = new DrawingPanel();
    private static Color currentColor = Color.BLACK;
    private static Shape currentShape = Shape.LINE;
    private static String inputText = "Text";

    public enum Shape {
        LINE, RECTANGLE, OVAL, ROUNDED_RECTANGLE, TEXT, FREELINE
    }

    public DrawingApp() {
        setTitle("Drawing Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(drawingPanel);
        setSize(600, 400);
        setVisible(true);
    }

    public static void setCurrentColor(Color color) {
        currentColor = color;
    }

    public static void setCurrentShape(Shape shape) {
        currentShape = shape;
        if (shape == Shape.TEXT) {
            inputText = JOptionPane.showInputDialog("Enter the text to display:");
        }
    }

    class DrawingPanel extends JPanel {
        private ArrayList<Drawable> drawables = new ArrayList<>();
        private Point startPoint, endPoint;

        DrawingPanel() {
            setBackground(Color.WHITE);
            MouseAdapter mouseAdapter = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    startPoint = e.getPoint();
                    if (currentShape != Shape.FREELINE) {
                        endPoint = startPoint;
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    if (currentShape != Shape.FREELINE) {
                        endPoint = e.getPoint();
                        drawables.add(new Drawable(currentShape, startPoint, endPoint, currentColor, inputText));
                        repaint();
                    }
                }

                public void mouseDragged(MouseEvent e) {
                    if (currentShape == Shape.FREELINE) {
                        endPoint = e.getPoint();
                        drawables.add(new Drawable(Shape.FREELINE, startPoint, endPoint, currentColor, inputText));
                        startPoint = endPoint;
                        repaint();
                    }
                }
            };
            addMouseListener(mouseAdapter);
            addMouseMotionListener(mouseAdapter);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Drawable d : drawables) {
                g.setColor(d.color);
                switch (d.shape) {
                    case LINE:
                        g.drawLine(d.startPoint.x, d.startPoint.y, d.endPoint.x, d.endPoint.y);
                        break;
                    case RECTANGLE:
                        g.drawRect(Math.min(d.startPoint.x, d.endPoint.x), Math.min(d.startPoint.y, d.endPoint.y),
                                Math.abs(d.endPoint.x - d.startPoint.x), Math.abs(d.endPoint.y - d.startPoint.y));
                        break;
                    case OVAL:
                        g.drawOval(Math.min(d.startPoint.x, d.endPoint.x), Math.min(d.startPoint.y, d.endPoint.y),
                                Math.abs(d.endPoint.x - d.startPoint.x), Math.abs(d.endPoint.y - d.startPoint.y));
                        break;
                    case ROUNDED_RECTANGLE:
                        g.drawRoundRect(Math.min(d.startPoint.x, d.endPoint.x), Math.min(d.startPoint.y, d.endPoint.y),
                                Math.abs(d.endPoint.x - d.startPoint.x), Math.abs(d.endPoint.y - d.startPoint.y),
                                20, 20);
                        break;
                    case TEXT:
                        g.drawString(d.text, d.startPoint.x, d.startPoint.y);
                        break;
                    case FREELINE:
                        g.drawLine(d.startPoint.x, d.startPoint.y, d.endPoint.x, d.endPoint.y);
                        break;
                }
            }
        }
    }

    class Drawable {
        Shape shape;
        Point startPoint, endPoint;
        Color color;
        String text;

        Drawable(Shape shape, Point startPoint, Point endPoint, Color color, String text) {
            this.shape = shape;
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.color = color;
            this.text = text;
        }
    }

    public static void main(String[] args) {
        new DrawingApp();
    }
}



/*

// 자유롭게 그림 그리는 버전

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DrawingApp extends JFrame {
    private DrawingPanel drawingPanel = new DrawingPanel();
    private static Color currentColor = Color.BLACK;
    private static Shape currentShape = Shape.LINE;

    public enum Shape {
        LINE, RECTANGLE, OVAL, ROUNDED_RECTANGLE, TEXT, FREELINE
    }

    public DrawingApp() {
        setTitle("Drawing Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(drawingPanel);
        setSize(600, 400);
        setVisible(true);
    }

    public static void setCurrentColor(Color color) {
        currentColor = color;
    }

    public static void setCurrentShape(Shape shape) {
        currentShape = shape;
    }

    class DrawingPanel extends JPanel {
        private ArrayList<Drawable> drawables = new ArrayList<>();
        private Point startPoint, endPoint;

        DrawingPanel() {
            setBackground(Color.WHITE);
            MouseAdapter mouseAdapter = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    startPoint = e.getPoint();
                    if (currentShape != Shape.FREELINE) {
                        endPoint = startPoint;
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    if (currentShape != Shape.FREELINE) {
                        endPoint = e.getPoint();
                        drawables.add(new Drawable(currentShape, startPoint, endPoint, currentColor));
                        repaint();
                    }
                }

                public void mouseDragged(MouseEvent e) {
                    if (currentShape == Shape.FREELINE) {
                        endPoint = e.getPoint();
                        drawables.add(new Drawable(Shape.FREELINE, startPoint, endPoint, currentColor));
                        startPoint = endPoint;
                        repaint();
                    }
                }
            };
            addMouseListener(mouseAdapter);
            addMouseMotionListener(mouseAdapter);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Drawable d : drawables) {
                g.setColor(d.color);
                switch (d.shape) {
                    case LINE:
                        g.drawLine(d.startPoint.x, d.startPoint.y, d.endPoint.x, d.endPoint.y);
                        break;
                    case RECTANGLE:
                        g.drawRect(Math.min(d.startPoint.x, d.endPoint.x), Math.min(d.startPoint.y, d.endPoint.y),
                                Math.abs(d.endPoint.x - d.startPoint.x), Math.abs(d.endPoint.y - d.startPoint.y));
                        break;
                    case OVAL:
                        g.drawOval(Math.min(d.startPoint.x, d.endPoint.x), Math.min(d.startPoint.y, d.endPoint.y),
                                Math.abs(d.endPoint.x - d.startPoint.x), Math.abs(d.endPoint.y - d.startPoint.y));
                        break;
                    case ROUNDED_RECTANGLE:
                        g.drawRoundRect(Math.min(d.startPoint.x, d.endPoint.x), Math.min(d.startPoint.y, d.endPoint.y),
                                Math.abs(d.endPoint.x - d.startPoint.x), Math.abs(d.endPoint.y - d.startPoint.y),
                                20, 20);
                        break;
                    case TEXT:
                        g.drawString("Text", d.startPoint.x, d.startPoint.y);
                        break;
                    case FREELINE:
                        g.drawLine(d.startPoint.x, d.startPoint.y, d.endPoint.x, d.endPoint.y);
                        break;
                }
            }
        }
    }

    class Drawable {
        Shape shape;
        Point startPoint, endPoint;
        Color color;

        Drawable(Shape shape, Point startPoint, Point endPoint, Color color) {
            this.shape = shape;
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.color = color;
        }
    }

    public static void main(String[] args) {
        new DrawingApp();
    }
}

*/


/*

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

*/



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