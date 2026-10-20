
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author vitor
 */
public class instrucoesJogo extends javax.swing.JFrame {
    
    int jogo = 0;

    /**
     * Creates new form contagem
     */
    public instrucoesJogo(int jogoEscolhido) {
        initComponents();
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setTitle("Como jogar?");
        carregarFontePersonalizada();
        
        mostraInstrucao(jogoEscolhido);
        jogo = jogoEscolhido;
    }
    
    void mostraInstrucao(int escolha){
        switch(escolha){
            case 1:
                tituloInstrucoes_lb.setText("CONTAGEM");
                instrucoes_lb.setText("<html>"
                + "<br>Instruções para jogar o jogo da Contagem!"
                + "<br><br>É um jogo bem simples, bem no alto da janela aparecerá um número escrito por extenso e sua tarefa é entender o número e através das setas abaixo"
                + "escrever o mesmo número corretamente! Cada digito representa uma casa decimal, sendo do mais a esquerda ao mais a direita a casa dos milhares, centenas, "
                + "dezenas e unidades! Ao clicar na seta apontada para cima os números naquela casa decimal irão aumentar de 0 até 9 iniciando novamente ao passar pelos extremos"
                + "ao clicar na seta apontada para baixo os números irão diminuir repetindo o ciclo de 9 a 0!"
                + "</html>");                       
                break;
            case 2:
                instrucoes_lb.setText("Qual o Resultado?");
                tituloInstrucoes_lb.setText("Qual o Resultado");
                instrucoes_lb.setText("<html>"
                + "<br>Instruções para jogar o jogo Qual o Resultado!"
                + "<br><br>Neste jogo são apresentados números e um operador matemático de soma, subtração, multiplicação ou divisão. Atráves dessas informações você precisa"
                + "chegar ao resultado e clicar em uma das quatro opções apresentadas nos botões à tela! Os números são gerados automaticamente assim como a operação, então "
                + "a variadade de operações é enorme! Ao acertar respostas seu contador de acertos aumenta e com isso a dificuldade também cresce, com ela os números vão ficando"
                + "maiores tornando os cálculos mais difíceis de se realizar!"
                + "</html>");
                break;
            case 3:
                tituloInstrucoes_lb.setText("INCÓGNITA");
                instrucoes_lb.setText("<html>"
                + "<br>Instruções para jogar o jogo da Incógnita!"
                + "<br><br>Neste jogo são apresentados números e um operador matemático de soma, subtração, multiplicação ou divisão. Atráves dessas informações você precisa"
                + "chegar ao resultado e clicar em uma das quatro opções apresentadas nos botões à tela! Similar ao jogo das Operações, no entanto agora não será apenas o resultado"
                + "que você deve descobrir, pois os valores são apresentados mas, um dos valores estará oculto e precisa ser descoberto. O valor escondido é aleatório podendo"
                + "ser o número 1, número 2, o resultado e até mesmo o operador! Cabe a você realizar o cálculo e descobrir o número escondido! Os números são gerados "
                + "automaticamente assim como a operação, resultando em diversas possibilidades"
                + "Ao acertar respostas seu contador de acertos aumenta e com isso a dificuldade também cresce, com ela os números vão ficando"
                + "maiores, além disso na dificuldade mais alta o operador do cálculo passa a poder ser escondido também!"
                + "</html>"); 
                break;
            default:
                instrucoes_lb.setText("ERRO \nNenhum jogo foi selecionado!");
        }
    }

    private void carregarFontePersonalizada() {
        try {
            InputStream is = getClass().getResourceAsStream("/Font/CrayonHandRegular2016Demo.ttf");
            
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            
            Font fontePronta = customFont.deriveFont(Font.BOLD, 20F);
            
            
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontePronta);
            
            jogar_btn.setFont(fontePronta);
            voltar_btn.setFont(fontePronta);
            
            fontePronta = customFont.deriveFont(Font.BOLD, 25f);
            tituloInstrucoes_lb.setFont(fontePronta);
            
            
            fontePronta = customFont.deriveFont(Font.BOLD, 45F);
            titulo_lb.setFont(fontePronta);
            
            fontePronta = customFont.deriveFont(Font.PLAIN, 18F);
            instrucoes_lb.setFont(fontePronta);
            
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a fonte externa. Usando fonte padrão.");
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
        logo_lb = new javax.swing.JLabel();
        instrucoes_panel = new javax.swing.JPanel();
        instrucoes_lb = new javax.swing.JLabel();
        tituloInstrucoes_lb = new javax.swing.JLabel();
        voltar_btn = new javax.swing.JButton();
        jogar_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(154, 113, 48));
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanel1.setBackground(new java.awt.Color(0, 48, 10));
        jPanel1.setPreferredSize(new java.awt.Dimension(960, 620));

        titulo_lb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        titulo_lb.setForeground(new java.awt.Color(255, 255, 255));
        titulo_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo_lb.setText("INSTRUÇÕES");
        titulo_lb.setPreferredSize(new java.awt.Dimension(170, 48));

        logo_lb.setForeground(new java.awt.Color(255, 255, 255));
        logo_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo_lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/quadro-menor.png"))); // NOI18N
        logo_lb.setPreferredSize(new java.awt.Dimension(200, 130));

        instrucoes_panel.setBackground(new java.awt.Color(0, 48, 10));
        instrucoes_panel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        instrucoes_panel.setPreferredSize(new java.awt.Dimension(700, 360));

        instrucoes_lb.setBackground(new java.awt.Color(0, 48, 10));
        instrucoes_lb.setForeground(new java.awt.Color(255, 255, 255));
        instrucoes_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        instrucoes_lb.setText("INSTRUÇÕES DE COMO JOGAR O JOGO");
        instrucoes_lb.setToolTipText("");
        instrucoes_lb.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        instrucoes_lb.setPreferredSize(new java.awt.Dimension(680, 318));

        tituloInstrucoes_lb.setBackground(new java.awt.Color(0, 0, 0));
        tituloInstrucoes_lb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tituloInstrucoes_lb.setForeground(new java.awt.Color(255, 255, 255));
        tituloInstrucoes_lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloInstrucoes_lb.setText("TITULO");
        tituloInstrucoes_lb.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        tituloInstrucoes_lb.setPreferredSize(new java.awt.Dimension(700, 40));

        javax.swing.GroupLayout instrucoes_panelLayout = new javax.swing.GroupLayout(instrucoes_panel);
        instrucoes_panel.setLayout(instrucoes_panelLayout);
        instrucoes_panelLayout.setHorizontalGroup(
            instrucoes_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tituloInstrucoes_lb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(instrucoes_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instrucoes_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        instrucoes_panelLayout.setVerticalGroup(
            instrucoes_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(instrucoes_panelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(tituloInstrucoes_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instrucoes_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        voltar_btn.setBackground(new java.awt.Color(0, 48, 10));
        voltar_btn.setForeground(new java.awt.Color(255, 255, 255));
        voltar_btn.setText("VOLTAR");
        voltar_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltar_btnActionPerformed(evt);
            }
        });

        jogar_btn.setBackground(new java.awt.Color(0, 48, 10));
        jogar_btn.setForeground(new java.awt.Color(255, 255, 255));
        jogar_btn.setText("JOGAR");
        jogar_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogar_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(logo_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(titulo_lb, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jogar_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(voltar_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(237, 237, 237))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(instrucoes_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(logo_lb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(titulo_lb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(instrucoes_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(voltar_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jogar_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
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
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jogar_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jogar_btnActionPerformed
        switch(jogo){
            case 1:
                contagem janela_contagem = new contagem();
        
                this.dispose();
                janela_contagem.setVisible(true);
                break;
            case 2:
                operacoes janela_operacoes = new operacoes();
        
                this.dispose();
                janela_operacoes.setVisible(true);
                break;
            case 3:
                incognita janela_incognita = new incognita();
        
                this.dispose();
                janela_incognita.setVisible(true);
                break;
            default:
                instrucoes_lb.setText("ERRO!! \nNenhum jogo foi selecionado!");
        }
    }//GEN-LAST:event_jogar_btnActionPerformed

    private void voltar_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltar_btnActionPerformed
        selecaoJogos janela_selecao = new selecaoJogos();
        
        this.dispose();
        janela_selecao.setVisible(true);
    }//GEN-LAST:event_voltar_btnActionPerformed

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
            java.util.logging.Logger.getLogger(instrucoesJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(instrucoesJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(instrucoesJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(instrucoesJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new instrucoesJogo(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel instrucoes_lb;
    private javax.swing.JPanel instrucoes_panel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jogar_btn;
    private javax.swing.JLabel logo_lb;
    private javax.swing.JLabel tituloInstrucoes_lb;
    private javax.swing.JLabel titulo_lb;
    private javax.swing.JButton voltar_btn;
    // End of variables declaration//GEN-END:variables
}
