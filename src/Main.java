
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Main {
    public int test;
   static ArrayList<Perceptron> perceptrons;
   static JButton del_btn;
   static JPanel the_application_panel;
   static Boolean never_started = true;
   static JLabel roz2_lb;
   static JLabel roz1_lb;
   static String[] rozenblat_frazes;
   static int prev_rez=0;


    public static void main(String[] args) {


        JFrame f=new JFrame("Perceptron_traning_tool");
        rozenblat_frazes = made_up_pharases();
      //  JLabel l=new JLabel("Anurag jain(csanuragjain)");
       // f.add(l);
        f.setSize(1000,800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
       // JButton b1 = new JButton();
       // JPanel main_panel =new Jpanel_for_line();
        JPanel main_panel =new JPanel();
       // main_panel.setLayout(new GridLayout(1,3));
        main_panel.setAlignmentY(Component.TOP_ALIGNMENT);
        main_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
       // JPanel glass_p=(JPanel)f.getGlassPane();
      //  Jpanel_for_line jfl = new Jpanel_for_line();
        //jfl.setBackground(Color.black);
     //   jfl.setPreferredSize(new Dimension(400,400));
     //   jfl.setOpaque(false);
    //    glass_p.add(jfl);
     //   glass_p.setVisible(true);
        //Нижняя панелька для управления приложением.
        JPanel bottom_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom_panel.setAlignmentY(Component.TOP_ALIGNMENT);
        bottom_panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton add_btn = new JButton(new Add_button_pressed(main_panel,f));
        add_btn.setText("ADD");

        del_btn = new JButton(new Del_button_pressed(main_panel));
        del_btn.setText("DEL");

        JButton compute_btn = new JButton(new Calculate_button_pressed(main_panel,f));
        compute_btn.setText("Compute");

        bottom_panel.add(add_btn);
        bottom_panel.add(del_btn);
        bottom_panel.add(compute_btn);
        del_btn.setEnabled(false);


        //Добовляем розенблата
        ImageIcon imgThisImg = new ImageIcon("res/file/ros1.jpg");
        ImageIcon imgThisImg2 = new ImageIcon("res/file/wpid-clouds-border2.png");

        JPanel rozenblat_jp = new JPanel();
        rozenblat_jp.setLayout(new BoxLayout(rozenblat_jp, BoxLayout.Y_AXIS));
        roz1_lb = new JLabel("тест");


    //    roz1_lb.setIcon(imgThisImg2);
      //  roz1_lb.setHorizontalTextPosition(JLabel.CENTER);
     //   roz1_lb.setHorizontalAlignment(SwingConstants.CENTER);
   //     roz1_lb.setVerticalTextPosition(SwingConstants.TOP);
      roz1_lb.setBorder(BorderFactory.createCompoundBorder(
              BorderFactory.createMatteBorder(5, 5, 5, 5, imgThisImg),
              BorderFactory.createEmptyBorder(0, 0, 0, 27)));

        roz2_lb = new JLabel("");
        roz2_lb.setIcon(imgThisImg);
        rozenblat_jp.add(roz1_lb);
        rozenblat_jp.add(roz2_lb);

        roz2_lb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                random_phraze();
                            }

        });




        rozenblat_jp.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        rozenblat_jp.setAlignmentX(Component.CENTER_ALIGNMENT);



        //Панелька картинки
        //Панелька приложения
        the_application_panel = new JPanel();
        the_application_panel.add(main_panel);
        the_application_panel.add(bottom_panel);
        the_application_panel.add(rozenblat_jp);
        //the_application_panel.setLayout();
        the_application_panel.setAlignmentY(Component.TOP_ALIGNMENT);
        the_application_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        f.add(the_application_panel);


        perceptrons = new ArrayList<Perceptron>();
        perceptrons.add(new Perceptron(f,main_panel,true));
        random_phraze();

    }

    static void random_phraze()
    {
        Random generator = new Random();
        int i = generator.nextInt(rozenblat_frazes.length);

        if (i == prev_rez)
        {
            if (i+1<rozenblat_frazes.length)
            {
                roz1_lb.setText(rozenblat_frazes[i+1]);
                prev_rez=i+1;

            } else if (i-1 >0) {
                roz1_lb.setText(rozenblat_frazes[i-1]);
                prev_rez = i-1;
            }

            }
                    else
        {
            prev_rez=i;
            roz1_lb.setText(rozenblat_frazes[i]);
        }
    }




    static class Calculate_button_pressed extends AbstractAction
    {

        JPanel link_to_main_panel;
        JFrame link_to_main_frame;


        public Calculate_button_pressed(JPanel link_to_main_panel, JFrame link_to_main_frame) {
            this.link_to_main_panel = link_to_main_panel;
            this.link_to_main_frame = link_to_main_frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Iterator<Perceptron> it = perceptrons.iterator();
            Perceptron p = it.next();
            double[] weights_ar = p.get_all_weights();
            double[] inputs_ar = p.get_all_xs();
            double treshold = p.get_treshhold();
            double bias = p.get_bias();
            double out = 0;
            double sum=0;
            String s1 ="";
            String s2 ="";
            Perceptron_controller pc = new Perceptron_controller(weights_ar,inputs_ar,treshold,bias);
            pc.compute();
            //update all
            p.set_out(pc.responce.out);
            p.set_sum(pc.responce.sum);
            p.append_text_results(pc.responce.computatuional_process,pc.responce.computatuional_process_with_numbers);

            //Рисовалка схемы
            ArrayList<Line_coordinates> line_dots = new ArrayList<>(); // координаты для линий
            ArrayList<Line_coordinates> oval_dots = new ArrayList<>(); // координты для овалов
            ArrayList<Str_coord> str_dots = new ArrayList<>(); // координаты текста

            int g_x_o = 20;//глобальный оффсет по x
            int g_y_o = 50;//глобальный оффсет по y
            int len = weights_ar.length; //колличество линий в персептроне.
            int y_offset = 0;//текущий оффсет от ирик номер элемента.
            int y_plus_with_each = 25; // за каждый раз сколько прибавлять
            int percept_number = 1;
            int x_offcet_for_each_per =590; //
            final int line_length = 150; //
            final int line_length_out = 170; //


           //в зависомости от номера персептрона
            int temp_x1 = calc_offset(percept_number,x_offcet_for_each_per) + g_x_o;
            int temp_y1 = g_y_o;

                        //Круг
                        int oval_w = 90;
                        int oval_h = 90;
                        int oval_x = temp_x1+line_length+oval_w/2+30;
                        int oval_y =  temp_y1 + (len*y_plus_with_each)/2 -oval_h/2+y_plus_with_each/2;


            oval_dots.add(new Line_coordinates(oval_x,oval_y,oval_w,oval_h)); //овал персептрона
            line_dots.add(new Line_coordinates(oval_x+oval_w,oval_y+oval_h/2,oval_x+oval_w/2+line_length_out,oval_y+oval_h/2)); //линия выхода
            StringBuffer sb_out = new StringBuffer().append("Sum:= ").append(pc.responce.sum).append("  OUT:= ").append(pc.responce.out);
            str_dots.add(new Str_coord((oval_x+oval_w/2+line_length_out)-line_length_out/2-35,oval_y+oval_h/2-2,sb_out.toString()));//Подпись на выход.
            StringBuffer Sb_tresh = new StringBuffer().append("Bias:= ").append(bias);
            //str_dots.add(new Str_coord(oval_x+oval_w/2-80,oval_y+oval_h+15,pc.responce.computatuional_process));//процесс вычисления
            //str_dots.add(new Str_coord(oval_x+oval_w/2-80,oval_y+oval_h+30,pc.responce.computatuional_process_with_numbers));//процесс вычисления с цифрами
            System.out.println("Расстояние x =" + ((oval_x + oval_w / 2 + line_length_out + line_length) - temp_x1 ));



            //подпись в цетре круга
            str_dots.add(new Str_coord(oval_x+oval_w/2-30,oval_y+oval_h/2-10,Sb_tresh.toString()));
            Sb_tresh = new StringBuffer().append("Th:=").append(treshold);
            str_dots.add(new Str_coord(oval_x+oval_w/2-30,oval_y+oval_h/2+10,Sb_tresh.toString()));
            //Подпись в центр круга

            //Рисовать линию к следующем персептрону если есть следующий
            if (it.hasNext()) {
                line_dots.add(new Line_coordinates(oval_x + oval_w / 2 + line_length_out, oval_y + oval_h / 2, oval_x + oval_w / 2 + line_length_out + line_length, temp_y1+y_plus_with_each));
            }


            for (int i = 0; i < len; i++) //цикл для весов и инпутов
            {
              temp_y1+=y_plus_with_each;
              line_dots.add(new Line_coordinates(temp_x1,temp_y1,temp_x1+line_length,temp_y1));//линии прямые
                //текстовая фигня
                StringBuffer sb = new StringBuffer().append("X (").append(i).append(")=").append(inputs_ar[i]).append("   ").append("W(").append(i).append(")=").append(weights_ar[i]);
                str_dots.add(new Str_coord(((temp_x1+line_length)-line_length/2-40),temp_y1-2,sb.toString())); //подписи к линиям

              line_dots.add(new Line_coordinates(temp_x1+line_length,temp_y1,oval_x,oval_y+oval_h/2)); //линии к персептрону


            }

//Закончиллась картинка


            //WArning!
            if (pc.responce.sum==666)
            {
                if (never_started)
                {
                    //test music
                    Musthread mt = new Musthread();
                    Thread th1 = new Thread(mt);
                    th1.start();
                    ImageIcon imgThisImg2 = new ImageIcon("res/file/o6d0l_Jf-P8.jpg");
                    roz2_lb.setIcon(imgThisImg2);
                    never_started = false;
                }
            }





            while (it.hasNext())
            {

               percept_number++;
               p=it.next();
               p.set_first_weight(pc.responce.out);
                weights_ar = p.get_all_weights();
                inputs_ar = p.get_all_xs();
                treshold = p.get_treshhold();
                bias = p.get_bias();
                out = 0;
                sum=0;
                s1 ="";
                s2 ="";
                pc = new Perceptron_controller(weights_ar,inputs_ar,treshold,bias);
                pc.compute();
                //update all
                p.set_out(pc.responce.out);
                p.set_sum(pc.responce.sum);
                p.append_text_results(pc.responce.computatuional_process,pc.responce.computatuional_process_with_numbers);





                if (pc.responce.sum==666)
                {
                    if (never_started)
                    {
                        //test music
                        Musthread mt = new Musthread();
                        Thread th1 = new Thread(mt);
                        th1.start();
                        ImageIcon imgThisImg2 = new ImageIcon("res/file/o6d0l_Jf-P8.jpg");
                        roz2_lb.setIcon(imgThisImg2);
                        never_started = false;
                    }
                }

                //Начинаем рисовать картинку 2
                len = weights_ar.length;
                temp_x1 = calc_offset(percept_number,x_offcet_for_each_per) + g_x_o;
                temp_y1 = g_y_o;

                //Круг
                 oval_w = 90;
                 oval_h = 90;
                 oval_x = temp_x1+line_length+oval_w/2+30;
                 oval_y =  temp_y1 + (len*y_plus_with_each)/2 -oval_h/2+y_plus_with_each/2;


                oval_dots.add(new Line_coordinates(oval_x,oval_y,oval_w,oval_h)); //овал персептрона
                line_dots.add(new Line_coordinates(oval_x+oval_w,oval_y+oval_h/2,oval_x+oval_w/2+line_length_out,oval_y+oval_h/2)); //линия выхода
                 sb_out = new StringBuffer().append("Sum:= ").append(pc.responce.sum).append("  OUT:= ").append(pc.responce.out);
                str_dots.add(new Str_coord((oval_x+oval_w/2+line_length_out)-line_length_out/2-35,oval_y+oval_h/2-2,sb_out.toString()));//Подпись на выход.
                 Sb_tresh = new StringBuffer().append("Bias:= ").append(bias);
                //str_dots.add(new Str_coord(oval_x+oval_w/2-80,oval_y+oval_h+15,pc.responce.computatuional_process));//процесс вычисления
                //str_dots.add(new Str_coord(oval_x+oval_w/2-80,oval_y+oval_h+30,pc.responce.computatuional_process_with_numbers));//процесс вычисления с цифрами
                System.out.println("Расстояние x =" + ((oval_x + oval_w / 2 + line_length_out + line_length) - temp_x1 ));



                //подпись в цетре круга
                str_dots.add(new Str_coord(oval_x+oval_w/2-30,oval_y+oval_h/2-10,Sb_tresh.toString()));
                Sb_tresh = new StringBuffer().append("Th:=").append(treshold);
                str_dots.add(new Str_coord(oval_x+oval_w/2-30,oval_y+oval_h/2+10,Sb_tresh.toString()));
                //Подпись в центр круга

                //Рисовать линию к следующем персептрону если есть следующий
                if (it.hasNext()) {
                    line_dots.add(new Line_coordinates(oval_x + oval_w / 2 + line_length_out, oval_y + oval_h / 2, oval_x + oval_w / 2 + line_length_out + line_length, temp_y1+y_plus_with_each));

                }


                for (int i = 0; i < len; i++) //цикл для весов и инпутов
                {
                    temp_y1+=y_plus_with_each;
                    line_dots.add(new Line_coordinates(temp_x1,temp_y1,temp_x1+line_length,temp_y1));//линии прямые
                    //текстовая фигня
                    StringBuffer sb = new StringBuffer().append("X (").append(i).append(")=").append(inputs_ar[i]).append("   ").append("W(").append(i).append(")=").append(weights_ar[i]);
                    str_dots.add(new Str_coord(((temp_x1+line_length)-line_length/2-40),temp_y1-2,sb.toString())); //подписи к линиям
                   // System.out.println("Супер формула" + (((temp_x1+line_length)-temp_x1)/2-20));
                   // System.out.println("temp_x1" + temp_x1);
                    line_dots.add(new Line_coordinates(temp_x1+line_length,temp_y1,oval_x,oval_y+oval_h/2)); //линии к персептрону
                }




            }
            random_phraze();
            //рисуем схему в отдельном окне


            System.out.println(line_dots.get(0).toString());
            Drawning_board db = new Drawning_board(line_dots,oval_dots,str_dots);




        }
    }


    static int calc_offset (int number, int offset)
    {
        int ret = 0;
        for (int i = 1; i<number;i++)
        {
            ret+=offset;
        }
        return ret;
    }


    static class Drawning_board extends JFrame
    {

        ArrayList<Line_coordinates> lc; // аррей линий
        ArrayList<Line_coordinates> oc; // аррей овалов
        ArrayList<Str_coord> str_dots; //аррей стрингов


        public Drawning_board(ArrayList<Line_coordinates> lc, ArrayList<Line_coordinates> oc,ArrayList<Str_coord> str_dots) throws HeadlessException {

            setTitle("Схема");
            setSize(400,400);
            setVisible(true);
            this.lc=lc;
            this.oc=oc;
            this.str_dots=str_dots;

        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2 =  (Graphics2D)g;

          //  g2.setRenderingHint(
           //         RenderingHints.KEY_TEXT_ANTIALIASING,
          //          RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


            for (Line_coordinates l: lc) // выводим линии
            {
          //   System.out.println(l.toString());
             g.drawLine(l.x1,l.y1,l.x2,l.y2);
            }

            for (Line_coordinates ovl: oc) // выводим линии
            {
         //       System.out.println(ovl.toString());
                g.drawOval(ovl.x1,ovl.y1,ovl.x2,ovl.y2);
            }

            for (Str_coord stc:str_dots)
            {
                g.drawString(stc.s,stc.x,stc.y);
            }


        }
    }





    static String[] made_up_pharases()
    {
        String[] ret_arr;
        ArrayList<String> a = new ArrayList<>();
        a.add("<html>Пипл будте аккуратнее на морском транспорте<br></br>это я вам на своем опыте говорю");
        a.add("<html>Я делал экспериметы в своей лаболатории<br></br>и что то страшное случилось когда<br></br>у меня получилась сумма 666");
        a.add("<html>В мое время вот это был стиль<br></br>федора и плащ, это вам не блейд раннер");
        a.add("<html>Не отмечайте свой день рождения<br></br>на яхтах");
        a.add("<html>Роботы захватят мир<br></br>а киборги все заполонят");
         ret_arr =  new String[a.size()];
        a.toArray(ret_arr);
        return  ret_arr;
    }


    static class Add_button_pressed extends AbstractAction
    {

        JPanel link_to_main_panel;
        JFrame link_to_main_frame;


        public Add_button_pressed(JPanel link_to_main_panel, JFrame link_to_main_frame) {
            this.link_to_main_panel = link_to_main_panel;
            this.link_to_main_frame = link_to_main_frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {



            perceptrons.add(new Perceptron(this.link_to_main_frame,this.link_to_main_panel,false));
            del_btn.setEnabled(true);
            the_application_panel.validate();
            the_application_panel.repaint();



        }
    }




    static class Del_button_pressed extends AbstractAction
    {

        JPanel link_to_main_panel;

        public Del_button_pressed(JPanel link_to_main_panel) {
            this.link_to_main_panel = link_to_main_panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {


            int num = perceptrons.size();
            int last_elem = num-1;
            perceptrons.remove(last_elem);
            this.link_to_main_panel.remove(last_elem);

            if (perceptrons.size()-1 <=0)
            {
                del_btn.setEnabled(false);
            }

            the_application_panel.validate();
            the_application_panel.repaint();

        }
    }


}
