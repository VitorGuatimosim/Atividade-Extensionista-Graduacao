
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author vitor
 */
public class contagem extends javax.swing.JFrame {
    
    String[] UNIDADE = {"Um", "Dois", "Três", "Quatro", "Cinco","Seis", "Sete", "Oito", "Nove",};
    String[] DEZENA = {
            "Dez", "Vinte", "Trinta", "Quarenta", "Cinquenta","Sessenta", "Setenta", "Oitenta", "Noventa", 
            "Onze","Doze", "Treze", "Quatorze", "Quinze", "Dezesseis","Dezessete", "Dezoito", "Dezenove"};
    String[] CENTENA = {
            "Cento", "Duzentos", "Trezentos","Quatrocentos", "Quinhentos", "Seiscentos",
            "Setecentos", "Oitocentos", "Novecentos"};
        
    String[] QUALIFICAS = {"Mil"};
    
    int contadorM, contadorC, contadorD, contadorU, numero, acertos;
    
    /**
     * Creates new form contagem
     */
    public contagem() {
        initComponents();
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("Contagem");
        carregarFontePersonalizada();
        
        escreveNumero();
    }
    
    private void carregarFontePersonalizada() {
        try {
            InputStream is = getClass().getResourceAsStream("/Font/CrayonHandRegular2016Demo.ttf");
            
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            
            Font fontePronta = customFont.deriveFont(Font.BOLD, 30F);
            
            
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontePronta);
                        
            enviar_btn.setFont(fontePronta);
            
            fontePronta = customFont.deriveFont(Font.PLAIN, 24F);
            
            dificuldade_lb.setFont(fontePronta);
            acertos_lb.setFont(fontePronta);            
            
            fontePronta = customFont.deriveFont(Font.PLAIN, 26F);                        
                     
            numero_lb.setFont(fontePronta);
            
            maisC_btn.setFont(fontePronta);
            maisD_btn.setFont(fontePronta);
            maisM_btn.setFont(fontePronta);
            maisU_btn.setFont(fontePronta);
            
            menosC_btn.setFont(fontePronta);
            menosD_btn.setFont(fontePronta);
            menosM_btn.setFont(fontePronta);
            menosU_btn.setFont(fontePronta);
            
            fontePronta = customFont.deriveFont(Font.PLAIN, 50F); 
            
            unidade_lb.setFont(fontePronta);
            dezena_lb.setFont(fontePronta);
            centena_lb.setFont(fontePronta);
            milhar_lb.setFont(fontePronta);
            
            fontePronta = customFont.deriveFont(Font.BOLD, 40F);
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

    int somaValor(int contador){
        contador++;
        if(contador == 10){
            contador = 0;
        }
        return contador;
    }
    
    int subtraiValor(int contador){
        contador--; 
        if(contador == -1){
            contador = 9;
        }
        return contador; 
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
            contagem janela_contagem = new contagem();
            this.dispose();
            janela_contagem.setVisible(true);   
        }else if (escolha == 1){
            Principal janela_principal = new Principal();
            this.dispose();
            janela_principal.setVisible(true);
        }
    }
    
    void escreveNumero(){       
        int limiteAtual = defineDificuldade();
                
        acertos_lb.setText("Acertos: " + acertos);
        numero = gerarNumero(limiteAtual);
        String numeroExtenso = " ";
        
        
        String strNumero = String.valueOf(numero);
        if(strNumero.length() > 3){
            numeroExtenso += insereMilhar(strNumero, 0, 1);
        }else if(strNumero.length() > 2){
            numeroExtenso += insereCentena(strNumero, 0, 1);        
        }else if(strNumero.length() > 1){
            numeroExtenso += insereDezena(strNumero, 0, 1);
        }else if(strNumero.length() > 0){
            numeroExtenso += insereUnidade(strNumero, 0, 1);
        }
        
        numero_lb.setText(numeroExtenso);
    }
    
    String insereMilhar(String strNumero, int i, int f){
        int milhar = Integer.parseInt(strNumero.substring(i,f));        
        String numeroExtenso = "";
        
        if(milhar != 1){
            numeroExtenso += insereUnidade(strNumero, 0, 1) + " ";
        }
        
        numeroExtenso += QUALIFICAS[0]+ " " + insereCentena(strNumero, i+1, f+1);
        return numeroExtenso;
    }
    
    String insereCentena(String strNumero, int i, int f){
        int centena = Integer.parseInt(strNumero.substring(i,f));
        String numeroExtenso = "";
        switch(centena){
            case 1:
                numeroExtenso += CENTENA[0];
                break;
            case 2:
                numeroExtenso += CENTENA[1];
                break;
            case 3:
                numeroExtenso += CENTENA[2];
                break;
            case 4:
                numeroExtenso += CENTENA[3];
                break;
            case 5:
                numeroExtenso += CENTENA[4];
                break;
            case 6:
                numeroExtenso += CENTENA[5];
                break;
            case 7:
                numeroExtenso += CENTENA[6]; 
                break;
            case 8: 
                numeroExtenso += CENTENA[7];
                break;
            case 9:
                numeroExtenso += CENTENA[8];
                break;
        }
        numeroExtenso += insereDezena(strNumero, i+1, f+1);
        return numeroExtenso;
    }
    
    String insereDezena(String strNumero, int i, int f){
        int dezena = Integer.parseInt(strNumero.substring(i, f));
        String numeroExtenso = "";
        
        if(i > 0){
            numeroExtenso = " e ";
        }        
        
        if(dezena == 1){
            int unidade = Integer.parseInt(strNumero.substring(i+1,f+1));
            if(unidade == 0){
                numeroExtenso += DEZENA[0];
            }else{
                dezena = (dezena * 10) + unidade;
                
                switch(dezena){
                    case 11:
                        numeroExtenso += DEZENA[9];
                        break;
                    case 12:
                        numeroExtenso += DEZENA[10];
                        break;
                    case 13:
                        numeroExtenso += DEZENA[11];
                        break;
                    case 14:
                        numeroExtenso += DEZENA[12];
                        break;
                    case 15:
                        numeroExtenso += DEZENA[13];
                        break;
                    case 16:
                        numeroExtenso += DEZENA[14];
                        break;
                    case 17:
                        numeroExtenso += DEZENA[15]; 
                        break;
                    case 18: 
                        numeroExtenso += DEZENA[16];
                        break;
                    case 19: 
                        numeroExtenso += DEZENA[17];
                        break;
                    default:
                        numeroExtenso = "";
                        break;
                }
            }
        }else{
            switch(dezena){
                case 2:
                    numeroExtenso += DEZENA[1];
                    break;
                case 3:
                    numeroExtenso += DEZENA[2];
                    break;
                case 4:
                    numeroExtenso += DEZENA[3];
                    break;
                case 5:
                    numeroExtenso += DEZENA[4];
                    break;
                case 6:
                    numeroExtenso += DEZENA[5];
                    break;
                case 7:
                    numeroExtenso += DEZENA[6]; 
                    break;
                case 8: 
                    numeroExtenso += DEZENA[7];
                    break;
                case 9: 
                    numeroExtenso += DEZENA[8];
                    break;
                default:
                    numeroExtenso = "";
                    break;
            }  
            numeroExtenso += insereUnidade(strNumero, i+1, f+1);
        }
        return numeroExtenso;
    }
    
    String insereUnidade(String strNumero, int i, int f){
        int unidade = Integer.parseInt(strNumero.substring(i, f));
        String numeroExtenso = "";
        
        if(i > 0){
            numeroExtenso = " e ";
        }
        
        switch(unidade){
            case 1:
                numeroExtenso += UNIDADE[0];
                break;
            case 2:
                numeroExtenso += UNIDADE[1];
                break;
            case 3:
                numeroExtenso += UNIDADE[2];
                break;
            case 4:
                numeroExtenso += UNIDADE[3];
                break;
            case 5:
                numeroExtenso += UNIDADE[4];
                break;
            case 6:
                numeroExtenso += UNIDADE[5];
                break;
            case 7:
                numeroExtenso += UNIDADE[6]; 
                break;
            case 8: 
                numeroExtenso += UNIDADE[7];
                break;
            case 9:
                numeroExtenso += UNIDADE[8];
                break;
            default:
                numeroExtenso = "";
                break;
        }
        return numeroExtenso;
    }
    
    int defineDificuldade(){
        if(acertos >= 5 && acertos < 10){
            dificuldade_lb.setText("Dificuldade: Médio");
            return 999;
        }else if(acertos >= 10){
            dificuldade_lb.setText("Dificuldade: Difícil");
            return 9999;
        }else{
            dificuldade_lb.setText("Dificuldade: Fácil");
            return 99;
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
        numero_lb = new javax.swing.JLabel();
        milhar_lb = new javax.swing.JLabel();
        centena_lb = new javax.swing.JLabel();
        dezena_lb = new javax.swing.JLabel();
        unidade_lb = new javax.swing.JLabel();
        maisM_btn = new javax.swing.JButton();
        menosM_btn = new javax.swing.JButton();
        maisC_btn = new javax.swing.JButton();
        menosC_btn = new javax.swing.JButton();
        maisD_btn = new javax.swing.JButton();
        menosD_btn = new javax.swing.JButton();
        maisU_btn = new javax.swing.JButton();
        menosU_btn = new javax.swing.JButton();
        enviar_btn = new javax.swing.JButton();
        acertos_lb = new javax.swing.JLabel();
        dificuldade_lb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(154, 113, 48));
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanel1.setBackground(new java.awt.Color(0, 48, 10));
        jPanel1.setPreferredSize(new java.awt.Dimension(960, 620));

        titulo_lb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        titulo_lb.setForeground(new java.awt.Color(255, 255, 255));
        titulo_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo_lb.setText("CONTAGEM");
        titulo_lb.setPreferredSize(new java.awt.Dimension(380, 60));

        numero_lb.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        numero_lb.setForeground(new java.awt.Color(255, 255, 255));
        numero_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numero_lb.setText("1000");

        milhar_lb.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        milhar_lb.setForeground(new java.awt.Color(255, 255, 255));
        milhar_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        milhar_lb.setText("0");
        milhar_lb.setPreferredSize(new java.awt.Dimension(50, 81));

        centena_lb.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        centena_lb.setForeground(new java.awt.Color(255, 255, 255));
        centena_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        centena_lb.setText("0");
        centena_lb.setPreferredSize(new java.awt.Dimension(50, 81));

        dezena_lb.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        dezena_lb.setForeground(new java.awt.Color(255, 255, 255));
        dezena_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dezena_lb.setText("0");
        dezena_lb.setPreferredSize(new java.awt.Dimension(50, 81));

        unidade_lb.setFont(new java.awt.Font("Segoe UI", 0, 60)); // NOI18N
        unidade_lb.setForeground(new java.awt.Color(255, 255, 255));
        unidade_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        unidade_lb.setText("0");
        unidade_lb.setPreferredSize(new java.awt.Dimension(50, 81));

        maisM_btn.setBackground(new java.awt.Color(0, 48, 10));
        maisM_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        maisM_btn.setForeground(new java.awt.Color(255, 255, 255));
        maisM_btn.setText("^");
        maisM_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maisM_btnActionPerformed(evt);
            }
        });

        menosM_btn.setBackground(new java.awt.Color(0, 48, 10));
        menosM_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        menosM_btn.setForeground(new java.awt.Color(255, 255, 255));
        menosM_btn.setText("v");
        menosM_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menosM_btnActionPerformed(evt);
            }
        });

        maisC_btn.setBackground(new java.awt.Color(0, 48, 10));
        maisC_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        maisC_btn.setForeground(new java.awt.Color(255, 255, 255));
        maisC_btn.setText("^");
        maisC_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maisC_btnActionPerformed(evt);
            }
        });

        menosC_btn.setBackground(new java.awt.Color(0, 48, 10));
        menosC_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        menosC_btn.setForeground(new java.awt.Color(255, 255, 255));
        menosC_btn.setText("v");
        menosC_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menosC_btnActionPerformed(evt);
            }
        });

        maisD_btn.setBackground(new java.awt.Color(0, 48, 10));
        maisD_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        maisD_btn.setForeground(new java.awt.Color(255, 255, 255));
        maisD_btn.setText("^");
        maisD_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maisD_btnActionPerformed(evt);
            }
        });

        menosD_btn.setBackground(new java.awt.Color(0, 48, 10));
        menosD_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        menosD_btn.setForeground(new java.awt.Color(255, 255, 255));
        menosD_btn.setText("v");
        menosD_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menosD_btnActionPerformed(evt);
            }
        });

        maisU_btn.setBackground(new java.awt.Color(0, 48, 10));
        maisU_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        maisU_btn.setForeground(new java.awt.Color(255, 255, 255));
        maisU_btn.setText("^");
        maisU_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maisU_btnActionPerformed(evt);
            }
        });

        menosU_btn.setBackground(new java.awt.Color(0, 48, 10));
        menosU_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        menosU_btn.setForeground(new java.awt.Color(255, 255, 255));
        menosU_btn.setText("v");
        menosU_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menosU_btnActionPerformed(evt);
            }
        });

        enviar_btn.setBackground(new java.awt.Color(0, 48, 10));
        enviar_btn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        enviar_btn.setForeground(new java.awt.Color(255, 255, 255));
        enviar_btn.setText("ENVIAR");
        enviar_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviar_btnActionPerformed(evt);
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
        dificuldade_lb.setPreferredSize(new java.awt.Dimension(200, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(numero_lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(dificuldade_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(acertos_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(286, 286, 286)
                        .addComponent(titulo_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(enviar_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(346, 346, 346)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(menosM_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(maisM_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(milhar_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(25, 25, 25)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(centena_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(maisC_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(menosC_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(25, 25, 25)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(maisD_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dezena_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(menosD_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(25, 25, 25)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(menosU_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(unidade_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(maisU_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(294, Short.MAX_VALUE))
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
                .addGap(40, 40, 40)
                .addComponent(numero_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maisM_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maisC_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maisD_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maisU_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(milhar_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(centena_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(unidade_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dezena_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menosM_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(menosU_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(menosD_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(menosC_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addComponent(enviar_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
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
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void maisM_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maisM_btnActionPerformed
        contadorM = somaValor(contadorM);
        milhar_lb.setText("" + contadorM);
    }//GEN-LAST:event_maisM_btnActionPerformed

    private void menosM_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menosM_btnActionPerformed
        contadorM = subtraiValor(contadorM);
        milhar_lb.setText("" + contadorM);       
    }//GEN-LAST:event_menosM_btnActionPerformed

    private void maisC_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maisC_btnActionPerformed
        contadorC = somaValor(contadorC);
        centena_lb.setText("" + contadorC);
    }//GEN-LAST:event_maisC_btnActionPerformed

    private void menosC_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menosC_btnActionPerformed
        contadorC = subtraiValor(contadorC);
        centena_lb.setText("" + contadorC);    
    }//GEN-LAST:event_menosC_btnActionPerformed

    private void maisD_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maisD_btnActionPerformed
        contadorD = somaValor(contadorD);
        dezena_lb.setText("" + contadorD);
    }//GEN-LAST:event_maisD_btnActionPerformed

    private void menosD_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menosD_btnActionPerformed
        contadorD = subtraiValor(contadorD);
        dezena_lb.setText("" + contadorD);    
    }//GEN-LAST:event_menosD_btnActionPerformed

    private void maisU_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maisU_btnActionPerformed
        contadorU = somaValor(contadorU);
        unidade_lb.setText("" + contadorU);
    }//GEN-LAST:event_maisU_btnActionPerformed

    private void menosU_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menosU_btnActionPerformed
        contadorU = subtraiValor(contadorU);
        unidade_lb.setText("" + contadorU);    
    }//GEN-LAST:event_menosU_btnActionPerformed

    private void enviar_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviar_btnActionPerformed
        String numeroCompleto = "";
        
        if(milhar_lb.getText().equalsIgnoreCase("0")){
            if(centena_lb.getText().equalsIgnoreCase("0")){
                if(dezena_lb.getText().equalsIgnoreCase("0")){
                    numeroCompleto += unidade_lb.getText();
                }else{
                    numeroCompleto += dezena_lb.getText();
                    numeroCompleto += unidade_lb.getText();
                }
            }else{
                numeroCompleto += centena_lb.getText();
                numeroCompleto += dezena_lb.getText();
                numeroCompleto += unidade_lb.getText();
            }
        }else{
            numeroCompleto += milhar_lb.getText();
            numeroCompleto += centena_lb.getText();
            numeroCompleto += dezena_lb.getText();
            numeroCompleto += unidade_lb.getText();
        }
        
        System.out.println(String.valueOf(numero));
        if(numeroCompleto.equalsIgnoreCase(String.valueOf(numero))){
            
            acertos++;
            escreveNumero();            
            
            milhar_lb.setText("0");
            contadorM = 0;
            
            centena_lb.setText("0");
            contadorC = 0;
            
            dezena_lb.setText("0");
            contadorD = 0;
            
            unidade_lb.setText("0");
            contadorU = 0;           
            
        }else{
            menuDerrota();
        }
    }//GEN-LAST:event_enviar_btnActionPerformed

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
            java.util.logging.Logger.getLogger(contagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(contagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(contagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(contagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new contagem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acertos_lb;
    private javax.swing.JLabel centena_lb;
    private javax.swing.JLabel dezena_lb;
    private javax.swing.JLabel dificuldade_lb;
    private javax.swing.JButton enviar_btn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton maisC_btn;
    private javax.swing.JButton maisD_btn;
    private javax.swing.JButton maisM_btn;
    private javax.swing.JButton maisU_btn;
    private javax.swing.JButton menosC_btn;
    private javax.swing.JButton menosD_btn;
    private javax.swing.JButton menosM_btn;
    private javax.swing.JButton menosU_btn;
    private javax.swing.JLabel milhar_lb;
    private javax.swing.JLabel numero_lb;
    private javax.swing.JLabel titulo_lb;
    private javax.swing.JLabel unidade_lb;
    // End of variables declaration//GEN-END:variables
}
