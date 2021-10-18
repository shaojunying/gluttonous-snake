package shao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import java.util.Random;

public class SnackPanel extends JPanel implements KeyListener, ActionListener {
    ImageIcon title = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/img/title.jpg")));
    ImageIcon body = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/img/body.jpg")));
    ImageIcon up = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/img/up.jpg")));
    ImageIcon down = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/img/down.jpg")));
    ImageIcon left = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/img/left.jpg")));
    ImageIcon right = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/img/right.jpg")));
    ImageIcon food = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/img/food.jpg")));

    int[] snakex = new int[750];
    int[] snakey = new int[750];
    Random random = new Random();
    int foodx = random.nextInt(34) * 25 + 25;
    int foody = random.nextInt(24) * 25 + 75;

    int len = 3;
    char direction = 'R';

    boolean isStarted = false;
    boolean isFailed = false;

    Timer timer = new Timer(100, this);

    public SnackPanel() {
        this.setFocusable(true);
        this.addKeyListener(this);
        setUp();
        timer.start();
    }

    //画所有东西
    public void paint(Graphics g) {
        this.setBackground(Color.WHITE);
        title.paintIcon(this, g, 25, 11);
        g.fillRect(25, 75, 850, 600);
        if (!isStarted) {
            g.setColor(Color.RED);
            g.setFont(new Font("华文行楷", Font.BOLD, 50));
            g.drawString("按空格键开始/暂停游戏", 170, 300);
        }
        if (isFailed) {
            g.setColor(Color.RED);
            g.setFont(new Font("华文行楷", Font.BOLD, 50));
            g.drawString("蛇撞到自己的身体,游戏结束", 120, 300);
        }
        //画蛇头
        switch (direction) {
            case 'U':
                up.paintIcon(this, g, snakex[0], snakey[0]);
                break;
            case 'D':
                down.paintIcon(this, g, snakex[0], snakey[0]);
                break;
            case 'L':
                left.paintIcon(this, g, snakex[0], snakey[0]);
                break;
            case 'R':
                right.paintIcon(this, g, snakex[0], snakey[0]);
                break;
        }
        //画蛇的身体
        for (int i = 1; i < len; i++) {
            body.paintIcon(this, g, snakex[i], snakey[i]);
        }
        food.paintIcon(this, g, foodx, foody);
        g.setColor(Color.red);
        g.setFont(new Font("华文行楷", Font.BOLD, 25));
        g.drawString("分数：" + (len - 3) * 100, 750, 40);
    }

    //初始化
    public void setUp() {
        len = 3;
        direction = 'R';
        snakex[0] = 100;
        snakey[0] = 100;
        snakex[1] = 75;
        snakey[1] = 100;
        snakex[2] = 50;
        snakey[2] = 100;
        isStarted = false;
        isFailed = false;
    }

    //键盘的监控
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_SPACE:
                if (isFailed) {
                    setUp();
                } else {
                    isStarted = !isStarted;
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D' && isStarted)
                    direction = 'U';
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U' && isStarted)
                    direction = 'D';
                break;
            case KeyEvent.VK_LEFT:
                if (direction != 'R' && isStarted)
                    direction = 'L';
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L' && isStarted)
                    direction = 'R';
                break;
        }
    }

    //时间监控器
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStarted && !isFailed) {
            //再定一个闹钟
            timer.start();
            //移动数据
            //移动蛇的身体
            for (int i = len; i > 0; i--) {
                snakex[i] = snakex[i - 1];
                snakey[i] = snakey[i - 1];
            }
            //移动蛇头
            switch (direction) {
                case 'U':
                    snakey[0] -= 25;
                    if (snakey[0] < 75) {
                        snakey[0] = 650;
                    }
                    break;
                case 'D':
                    snakey[0] += 25;
                    if (snakey[0] > 650) {
                        snakey[0] = 75;
                    }
                    break;
                case 'L':
                    snakex[0] -= 25;
                    if (snakex[0] < 25) {
                        snakex[0] = 850;
                    }
                    break;
                case 'R':
                    snakex[0] += 25;
                    if (snakex[0] > 850) {
                        snakex[0] = 25;
                    }
                    break;
            }
            if (snakex[0] == foodx && snakey[0] == foody) {
                len++;
                foodx = random.nextInt(34) * 25 + 25;
                foody = random.nextInt(24) * 25 + 75;
            }
            for (int i = 1; i < len; i++) {
                if (snakex[i] == snakex[0] && snakey[i] == snakey[0]) {
                    isFailed = true;
                    break;
                }
            }
        }
        repaint();
    }
}
