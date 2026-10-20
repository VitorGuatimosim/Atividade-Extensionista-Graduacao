
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JOptionPane;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author vitor
 */
public class incognita extends javax.swing.JFrame {

    float numero1, numero2, resultado, botaoCerto;
    int operador, acertos;
    boolean modoDificil = false;
    String operadorCerto = "";
    
    /**
     * Creates new form incognita
     */
    public incognita() {
        initComponents();
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("Incógnita");
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
            
            numero1_txt_lb.setFont(fontePronta);           
            numero2_txt_lb.setFont(fontePronta);            
            operador_txt_lb.setFont(fontePronta);            
            resultado_txt_lb.setFont(fontePronta);
            
            igual_txt_lb.setFont(fontePronta);
            
            jLabel2.setFont(fontePronta);
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
        
        numero1 = gerarNumero(limiteAtual);
        numero2 = gerarNumero(limiteAtual);
        resultado = 0;
        
        numero1_lb.setText("" + numero1);
        numero2_lb.setText("" + numero2);
        acertos_lb.setText("Acertos: " + acertos);
        
        operador = gerarNumero(4);
        switch(operador){
            case 1:
                operador_lb.setText("+");
                resultado = numero1 + numero2;
                break;
            case 2:
                operador_lb.setText("-");
                resultado = numero1 - numero2;
                break;
            case 3:
                operador_lb.setText("*");
                resultado = numero1 * numero2;
                break;
            case 4:
                operador_lb.setText("÷");
                resultado = numero1 / numero2;
                break;
        }
        
        BigDecimal bd = new BigDecimal(Float.toString(resultado));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        resultado = bd.floatValue();
        
        resultado_lb.setText("" + resultado); 
        
        escondeNumero();
    }
    
    void escondeNumero(){
        int limite =3;
        
        if(modoDificil){
            limite = 4;
        }
        int opcaoEscondida = gerarNumero(limite);
        switch(opcaoEscondida){
            case 1:
                numero1_lb.setText("");
                botaoCerto = numero1;
                break;
            case 2:
                numero2_lb.setText("");
                botaoCerto = numero2;
                break;
            case 3:
                resultado_lb.setText("");
                botaoCerto = resultado;
                break;
            case 4:                
                operadorCerto = operador_lb.getText();
                operador_lb.setText("");
                break;
        }
        geraBotaoCerto();
    }
    
    void geraBotaoCerto(){
        int botao = gerarNumero(4);
        
        if(operadorCerto.equalsIgnoreCase("")){
            float[] numerosErrados = new float[3];
            geraRespostaErrada(numerosErrados, botaoCerto);
            switch(botao){
                case 1:
                    opcao1_btn.setText("" + botaoCerto);

                    opcao2_btn.setText("" + numerosErrados[0]);
                    opcao3_btn.setText("" + numerosErrados[1]); 
                    opcao4_btn.setText("" + numerosErrados[2]);
                    break;
                case 2:
                    opcao2_btn.setText("" + botaoCerto);

                    opcao1_btn.setText("" + numerosErrados[0]);
                    opcao3_btn.setText("" + numerosErrados[1]);
                    opcao4_btn.setText("" + numerosErrados[2]);
                    break;
                case 3:
                    opcao3_btn.setText("" + botaoCerto);

                    opcao2_btn.setText("" + numerosErrados[0]);
                    opcao1_btn.setText("" + numerosErrados[1]);
                    opcao4_btn.setText("" + numerosErrados[2]);
                    break;                
                case 4:
                    opcao4_btn.setText("" + botaoCerto);

                    opcao2_btn.setText("" + numerosErrados[0]);
                    opcao3_btn.setText("" + numerosErrados[1]);
                    opcao1_btn.setText("" + numerosErrados[2]);
                    break;
            }            
        }else{
            
            String[] operadorErrado = new String[3];
            geraOperadorErrado(operadorErrado);
            switch(botao){
                case 1:
                    opcao1_btn.setText("" + operadorCerto);

                    opcao2_btn.setText("" + operadorErrado[0]);
                    opcao3_btn.setText("" + operadorErrado[1]); 
                    opcao4_btn.setText("" + operadorErrado[2]);
                    break;
                case 2:
                    opcao2_btn.setText("" + operadorCerto);

                    opcao1_btn.setText("" + operadorErrado[0]);
                    opcao3_btn.setText("" + operadorErrado[1]);
                    opcao4_btn.setText("" + operadorErrado[2]);
                    break;
                case 3:
                    opcao3_btn.setText("" + operadorCerto);

                    opcao2_btn.setText("" + operadorErrado[0]);
                    opcao1_btn.setText("" + operadorErrado[1]);
                    opcao4_btn.setText("" + operadorErrado[2]);
                    break;                
                case 4:
                    opcao4_btn.setText("" + operadorCerto);

                    opcao2_btn.setText("" + operadorErrado[0]);
                    opcao3_btn.setText("" + operadorErrado[1]);
                    opcao1_btn.setText("" + operadorErrado[2]);
                    break;
            }
        }
    }
    
    void geraRespostaErrada(float[] respostasErradas, float resultado){
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
    
    void geraOperadorErrado(String[] operadorErrado){
        String aux; 
        for (int i = 0; i < 3; i++){
            do{
                switch(gerarNumero(6)){
                    case 1:
                        aux = "+";
                        break;
                    case 2:
                        aux = "-";
                        break;
                    case 3:
                        aux = "÷";
                        break;
                    case 4:
                        aux = "*";
                        break;
                    case 5: 
                        aux = "%";
                        break;
                    case 6:
                        aux = "$";
                        break;
                    default:
                        aux = "";
                        break;
                }
            }while(aux.equalsIgnoreCase(operadorCerto));
            operadorErrado[i] = aux;
        }
    
    }
    
    void verificaAcerto(String botao){
        try {
            if(Float.parseFloat(botao) == botaoCerto){
                acertos++;
                mostraOperacao();
            }else{
                menuDerrota();
            }
        } catch (NumberFormatException e) {
            if(botao.equalsIgnoreCase(operadorCerto)){
                acertos++;                
                operadorCerto = "";
                mostraOperacao();
            }else{
                menuDerrota();
            }
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
            incognita janela_incognita = new incognita();
            this.dispose();
            janela_incognita.setVisible(true);   
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
            modoDificil = true;
            return 25;
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
        numero1_txt_lb = new javax.swing.JLabel();
        numero1_lb = new javax.swing.JLabel();
        operador_txt_lb = new javax.swing.JLabel();
        operador_lb = new javax.swing.JLabel();
        numero2_txt_lb = new javax.swing.JLabel();
        numero2_lb = new javax.swing.JLabel();
        resultado_txt_lb = new javax.swing.JLabel();
        resultado_lb = new javax.swing.JLabel();
        opcao1_btn = new javax.swing.JButton();
        opcao2_btn = new javax.swing.JButton();
        opcao3_btn = new javax.swing.JButton();
        opcao4_btn = new javax.swing.JButton();
        igual_txt_lb = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dificuldade_lb = new javax.swing.JLabel();
        acertos_lb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(154, 113, 48));
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 700));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setVerifyInputWhenFocusTarget(false);

        jPanel1.setBackground(new java.awt.Color(0, 48, 10));
        jPanel1.setPreferredSize(new java.awt.Dimension(960, 620));

        titulo_lb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        titulo_lb.setForeground(new java.awt.Color(255, 255, 255));
        titulo_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo_lb.setText("INCÓGNITA");
        titulo_lb.setPreferredSize(new java.awt.Dimension(380, 60));

        numero1_txt_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        numero1_txt_lb.setForeground(new java.awt.Color(255, 255, 255));
        numero1_txt_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numero1_txt_lb.setText("NUMERO 1");

        numero1_lb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        numero1_lb.setForeground(new java.awt.Color(255, 255, 255));
        numero1_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numero1_lb.setText("1");

        operador_txt_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        operador_txt_lb.setForeground(new java.awt.Color(255, 255, 255));
        operador_txt_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        operador_txt_lb.setText("OPERADOR");

        operador_lb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        operador_lb.setForeground(new java.awt.Color(255, 255, 255));
        operador_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        operador_lb.setText("X");

        numero2_txt_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        numero2_txt_lb.setForeground(new java.awt.Color(255, 255, 255));
        numero2_txt_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numero2_txt_lb.setText("NUMERO 2");

        numero2_lb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        numero2_lb.setForeground(new java.awt.Color(255, 255, 255));
        numero2_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numero2_lb.setText("2");

        resultado_txt_lb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        resultado_txt_lb.setForeground(new java.awt.Color(255, 255, 255));
        resultado_txt_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultado_txt_lb.setText("RESULTADO");

        resultado_lb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        resultado_lb.setForeground(new java.awt.Color(255, 255, 255));
        resultado_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultado_lb.setText("2");

        opcao1_btn.setBackground(new java.awt.Color(0, 48, 10));
        opcao1_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        opcao1_btn.setForeground(new java.awt.Color(255, 255, 255));
        opcao1_btn.setText("1");
        opcao1_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao1_btnActionPerformed(evt);
            }
        });

        opcao2_btn.setBackground(new java.awt.Color(0, 48, 10));
        opcao2_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        opcao2_btn.setForeground(new java.awt.Color(255, 255, 255));
        opcao2_btn.setText("2");
        opcao2_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao2_btnActionPerformed(evt);
            }
        });

        opcao3_btn.setBackground(new java.awt.Color(0, 48, 10));
        opcao3_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        opcao3_btn.setForeground(new java.awt.Color(255, 255, 255));
        opcao3_btn.setText("3");
        opcao3_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao3_btnActionPerformed(evt);
            }
        });

        opcao4_btn.setBackground(new java.awt.Color(0, 48, 10));
        opcao4_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        opcao4_btn.setForeground(new java.awt.Color(255, 255, 255));
        opcao4_btn.setText("4");
        opcao4_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcao4_btnActionPerformed(evt);
            }
        });

        igual_txt_lb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        igual_txt_lb.setForeground(new java.awt.Color(255, 255, 255));
        igual_txt_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        igual_txt_lb.setText("=");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("------");

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

        dificuldade_lb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dificuldade_lb.setForeground(new java.awt.Color(255, 255, 255));
        dificuldade_lb.setText("Dificuldade: ");
        dificuldade_lb.setPreferredSize(new java.awt.Dimension(236, 30));

        acertos_lb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        acertos_lb.setForeground(new java.awt.Color(255, 255, 255));
        acertos_lb.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        acertos_lb.setText("Acertos: ");
        acertos_lb.setPreferredSize(new java.awt.Dimension(200, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(titulo_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(opcao1_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opcao3_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operador_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(operador_txt_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(opcao4_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opcao2_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(225, 225, 225))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(dificuldade_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(207, 207, 207))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numero1_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numero1_txt_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(212, 212, 212)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(numero2_txt_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numero2_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(igual_txt_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resultado_txt_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resultado_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(acertos_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(titulo_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dificuldade_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acertos_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(79, 79, 79)
                                    .addComponent(igual_txt_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(numero2_txt_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(28, 28, 28)
                                    .addComponent(numero2_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, 0)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(resultado_txt_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(29, 29, 29)
                            .addComponent(resultado_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(operador_txt_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(operador_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(numero1_txt_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(numero1_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcao2_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opcao1_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcao4_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(opcao3_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opcao2_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcao2_btnActionPerformed
        verificaAcerto(opcao2_btn.getText());
    }//GEN-LAST:event_opcao2_btnActionPerformed

    private void opcao1_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcao1_btnActionPerformed
        verificaAcerto(opcao1_btn.getText());
    }//GEN-LAST:event_opcao1_btnActionPerformed

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
            java.util.logging.Logger.getLogger(incognita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(incognita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(incognita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(incognita.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new incognita().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acertos_lb;
    private javax.swing.JLabel dificuldade_lb;
    private javax.swing.JLabel igual_txt_lb;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel numero1_lb;
    private javax.swing.JLabel numero1_txt_lb;
    private javax.swing.JLabel numero2_lb;
    private javax.swing.JLabel numero2_txt_lb;
    private javax.swing.JButton opcao1_btn;
    private javax.swing.JButton opcao2_btn;
    private javax.swing.JButton opcao3_btn;
    private javax.swing.JButton opcao4_btn;
    private javax.swing.JLabel operador_lb;
    private javax.swing.JLabel operador_txt_lb;
    private javax.swing.JLabel resultado_lb;
    private javax.swing.JLabel resultado_txt_lb;
    private javax.swing.JLabel titulo_lb;
    // End of variables declaration//GEN-END:variables
}
