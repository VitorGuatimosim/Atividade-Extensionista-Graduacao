/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author vitor
 */

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import javax.swing.JOptionPane;

public class operacoes extends javax.swing.JFrame {
    
    float resultado = 0;
    int acertos = 0;

    /**
     * Creates new form operacoes
     */
    public operacoes() {
        initComponents();
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("Qual o Resultado?");
        carregarFontePersonalizada();
        
        mostraOperacao();
    }
    
    private void carregarFontePersonalizada() {
        try {
            InputStream is = getClass().getResourceAsStream("/Font/CrayonHandRegular2016Demo.ttf");
            
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            
            Font fontePronta = customFont.deriveFont(Font.BOLD, 30F);
            
            
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontePronta);
                        
            fontePronta = customFont.deriveFont(Font.PLAIN, 24F);
            
            dificuldade_lb.setFont(fontePronta);
            acertos_lb.setFont(fontePronta);            
            
            fontePronta = customFont.deriveFont(Font.PLAIN, 26F);                        
                     
            textoNumero1_lb.setFont(fontePronta);           
            textoNumero2_lb.setFont(fontePronta);        
            resultado_txt_lb.setFont(fontePronta);
            
            textoIgual_lb.setFont(fontePronta);
            resultado_txt_lb.setFont(fontePronta);     
            
            jLabel3.setFont(fontePronta);
            jLabel4.setFont(fontePronta);
            jLabel5.setFont(fontePronta);      
            
            fontePronta = customFont.deriveFont(Font.BOLD, 45F);
            titulo_lb.setFont(fontePronta);
            
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a fonte externa. Usando fonte padrão.");
        }
    }
    
    int gerarNumero(int limite){
        Random gerador = new Random();
        return (int)gerador.nextInt(limite) + 1;
    }    
    
    void mostraOperacao(){
        int limiteAtual = defineDificuldade();
               
        int numero1 = gerarNumero(limiteAtual);
        int numero2 = gerarNumero(limiteAtual);
       
        numero1_lb.setText("" + numero1);
        numero2_lb.setText("" + numero2);
        acertos_lb.setText("Acertos: " + acertos);
       
        int operador = gerarNumero(4);
        switch(operador){
            case 1:
                operacao_lb.setText("+");
                resultado = numero1 + numero2;
                break;
            case 2:
                operacao_lb.setText("-");
                resultado = numero1 - numero2;
                break;
            case 3:
                operacao_lb.setText("*");
                resultado = numero1 * numero2;
                break;
            case 4:
                operacao_lb.setText("÷");
                resultado = numero1 / numero2;
                break;
        }
        
        BigDecimal bd = new BigDecimal(Float.toString(resultado));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        resultado = bd.floatValue();
       
        float[] numerosErrados = new float[3];
        geraRespostaErradas(numerosErrados, resultado);
       
        int botaoCerto = gerarNumero(4);

        switch(botaoCerto){
            case 1:
                opcao1_btn.setText("" + resultado);

                opcao2_btn.setText("" + numerosErrados[0]);
                opcao3_btn.setText("" + numerosErrados[1]); 
                opcao4_btn.setText("" + numerosErrados[2]);
                break;
            case 2:
                opcao2_btn.setText("" + resultado);

                opcao1_btn.setText("" + numerosErrados[0]);
                opcao3_btn.setText("" + numerosErrados[1]);
                opcao4_btn.setText("" + numerosErrados[2]);
                break;
            case 3:
                opcao3_btn.setText("" + resultado);

                opcao2_btn.setText("" + numerosErrados[0]);
                opcao1_btn.setText("" + numerosErrados[1]);
                opcao4_btn.setText("" + numerosErrados[2]);
                break;
            case 4:
                opcao4_btn.setText("" + resultado);

                opcao2_btn.setText("" + numerosErrados[0]);
                opcao3_btn.setText("" + numerosErrados[1]);
                opcao1_btn.setText("" + numerosErrados[2]);
                break;
        }
        acertos++;
    }
    
    void verificaAcerto(String botao){
        if(Float.parseFloat(botao) == resultado){
            mostraOperacao();
        }else{
            menuDerrota();
        }
    }
    
    void geraRespostaErradas(float[] respostasErradas, float resultado){
        int aux;
        int limite = (int)resultado + 10;
       
        if(limite < 0){
            limite *= -1;
        }
        
        for (int i = 0; i < 3; i++){
            aux = gerarNumero(limite);
           
            while(aux == resultado){
                aux = gerarNumero(limite);
            }
            respostasErradas[i] = aux;
        }
    }
    
    void menuDerrota(){
        String[] options = {"Tentar Novamente", "Voltar ao Menu Principal"};

        int escolha = JOptionPane.showOptionDialog(
            null,
            "Resposta Errada!!",
            "Você Perdeu",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
                options,
                options[0]
        );

        if (escolha == 0){
            operacoes janela_operacoes = new operacoes();
            this.dispose();
            janela_operacoes.setVisible(true);   
        }else if (escolha == 1){
            Principal janela_principal = new Principal();
            this.dispose();
            janela_principal.setVisible(true);
        }
    }
    
    int defineDificuldade(){
        if(acertos >= 5 && acertos < 10){
            dificuldade_lb.setText("Dificuldade: Médio");
            return 25;
        }else if(acertos >= 10){
            dificuldade_lb.setText("Dificuldade: Difícil");
            return 100;
        }else{
            dificuldade_lb.setText("Dificuldade: Fácil");
            return 10;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        titulo_lb = new javax.swing.JLabel();
        operacao_lb = new javax.swing.JLabel();
        numero1_lb = new javax.swing.JLabel();
        numero2_lb = new javax.swing.JLabel();
        resultado_txt_lb = new javax.swing.JLabel();
        textoNumero1_lb = new javax.swing.JLabel();
        textoNumero2_lb = new javax.swing.JLabel();
        textoIgual_lb = new javax.swing.JLabel();
        pontoInterrogacao_lb = new javax.swing.JLabel();
        opcao1_btn = new javax.swing.JButton();
        opcao2_btn = new javax.swing.JButton();
        opcao3_btn = new javax.swing.JButton();
        opcao4_btn = new javax.swing.JButton();
        acertos_lb = new javax.swing.JLabel();
        dificuldade_lb = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(154, 113, 48));
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanel1.setBackground(new java.awt.Color(0, 48, 10));
        jPanel1.setPreferredSize(new java.awt.Dimension(960, 620));

        titulo_lb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        titulo_lb.setForeground(new java.awt.Color(255, 255, 255));
        titulo_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo_lb.setText("QUAL O RESULTADO");
        titulo_lb.setPreferredSize(new java.awt.Dimension(380, 60));

        operacao_lb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        operacao_lb.setForeground(new java.awt.Color(255, 255, 255));
        operacao_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        operacao_lb.setText("X");

        numero1_lb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        numero1_lb.setForeground(new java.awt.Color(255, 255, 255));
        numero1_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        numero2_lb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        numero2_lb.setForeground(new java.awt.Color(255, 255, 255));
        numero2_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        resultado_txt_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        resultado_txt_lb.setForeground(new java.awt.Color(255, 255, 255));
        resultado_txt_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultado_txt_lb.setText("RESULTADO");

        textoNumero1_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoNumero1_lb.setForeground(new java.awt.Color(255, 255, 255));
        textoNumero1_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoNumero1_lb.setText("NUMERO 1");

        textoNumero2_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textoNumero2_lb.setForeground(new java.awt.Color(255, 255, 255));
        textoNumero2_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoNumero2_lb.setText("NUMERO 2");

        textoIgual_lb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        textoIgual_lb.setForeground(new java.awt.Color(255, 255, 255));
        textoIgual_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoIgual_lb.setText("=");

        pontoInterrogacao_lb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        pontoInterrogacao_lb.setForeground(new java.awt.Color(255, 255, 255));
        pontoInterrogacao_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pontoInterrogacao_lb.setText("?");

        opcao1_btn.setBackground(new java.awt.Color(0, 48, 10));
        opcao1_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        opcao1_btn.setForeground(new java.awt.Color(255, 255, 255));
        opcao1_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao1_btnActionPerformed(evt);
            }
        });

        opcao2_btn.setBackground(new java.awt.Color(0, 48, 10));
        opcao2_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        opcao2_btn.setForeground(new java.awt.Color(255, 255, 255));
        opcao2_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao2_btnActionPerformed(evt);
            }
        });

        opcao3_btn.setBackground(new java.awt.Color(0, 48, 10));
        opcao3_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        opcao3_btn.setForeground(new java.awt.Color(255, 255, 255));
        opcao3_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao3_btnActionPerformed(evt);
            }
        });

        opcao4_btn.setBackground(new java.awt.Color(0, 48, 10));
        opcao4_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        opcao4_btn.setForeground(new java.awt.Color(255, 255, 255));
        opcao4_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao4_btnActionPerformed(evt);
            }
        });

        acertos_lb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        acertos_lb.setForeground(new java.awt.Color(255, 255, 255));
        acertos_lb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        acertos_lb.setText("Acertos: ");
        acertos_lb.setPreferredSize(new java.awt.Dimension(200, 30));

        dificuldade_lb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dificuldade_lb.setForeground(new java.awt.Color(255, 255, 255));
        dificuldade_lb.setText("Dificuldade: ");
        dificuldade_lb.setPreferredSize(new java.awt.Dimension(236, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("------");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("------");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("------");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("------");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(dificuldade_lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(acertos_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(opcao3_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(opcao1_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(100, 100, 100)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(opcao2_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(opcao4_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textoNumero1_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(numero1_lb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(operacao_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(numero2_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(textoIgual_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(textoNumero2_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(100, 100, 100)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(resultado_txt_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(pontoInterrogacao_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(titulo_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(titulo_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acertos_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dificuldade_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNumero1_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNumero2_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resultado_txt_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numero2_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operacao_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numero1_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textoIgual_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pontoInterrogacao_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(opcao1_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opcao2_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(opcao3_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(opcao4_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {pontoInterrogacao_lb, textoIgual_lb});

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opcao1_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcao1_btnActionPerformed
        verificaAcerto(opcao1_btn.getText());
    }//GEN-LAST:event_opcao1_btnActionPerformed

    private void opcao2_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcao2_btnActionPerformed
        verificaAcerto(opcao2_btn.getText());
    }//GEN-LAST:event_opcao2_btnActionPerformed

    private void opcao3_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcao3_btnActionPerformed
        verificaAcerto(opcao3_btn.getText());
    }//GEN-LAST:event_opcao3_btnActionPerformed

    private void opcao4_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcao4_btnActionPerformed
        verificaAcerto(opcao4_btn.getText());
    }//GEN-LAST:event_opcao4_btnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(operacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(operacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(operacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(operacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new operacoes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acertos_lb;
    private javax.swing.JLabel dificuldade_lb;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel numero1_lb;
    private javax.swing.JLabel numero2_lb;
    private javax.swing.JButton opcao1_btn;
    private javax.swing.JButton opcao2_btn;
    private javax.swing.JButton opcao3_btn;
    private javax.swing.JButton opcao4_btn;
    private javax.swing.JLabel operacao_lb;
    private javax.swing.JLabel pontoInterrogacao_lb;
    private javax.swing.JLabel resultado_txt_lb;
    private javax.swing.JLabel textoIgual_lb;
    private javax.swing.JLabel textoNumero1_lb;
    private javax.swing.JLabel textoNumero2_lb;
    private javax.swing.JLabel titulo_lb;
    // End of variables declaration//GEN-END:variables
}
