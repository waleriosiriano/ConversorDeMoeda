import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverter {

    private static final String BASE_URL = "https://economia.awesomeapi.com.br/json/last/";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> criarInterface());
    }

    private static void criarInterface() {
        JFrame frame = new JFrame("Conversor de Moeda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // moedas suportadas
        String[] moedas = {"USD", "BRL", "EUR", "BTC", "JPY"};

        // Dropdowns para moedas de origem e destino
        JLabel labelOrigem = new JLabel("Moeda origem:");
        JComboBox<String> dropdownOrigem = new JComboBox<>(moedas);
        JLabel labelDestino = new JLabel("Moeda destino:");
        JComboBox<String> dropdownDestino = new JComboBox<>(moedas);

        // Entrada para o valor
        JLabel labelValor = new JLabel("Valor:");
        JTextField campoValor = new JTextField();

        // Botão para converter
        JButton botaoConverter = new JButton("Converter");

        // Label exibir o resultado
        JLabel resultado = new JLabel("Resultado: ");

        // componentes do painel
        panel.add(labelOrigem);
        panel.add(dropdownOrigem);
        panel.add(labelDestino);
        panel.add(dropdownDestino);
        panel.add(labelValor);
        panel.add(campoValor);
        panel.add(botaoConverter);
        panel.add(new JLabel()); // Espaço vazio
        panel.add(resultado);

        // Ação do botão "Converter"
        botaoConverter.addActionListener(e -> {
            String moedaOrigem = dropdownOrigem.getSelectedItem().toString();
            String moedaDestino = dropdownDestino.getSelectedItem().toString();
            double valor;

            try {
                valor = Double.parseDouble(campoValor.getText());
                double resultadoConversao = converterMoeda(moedaOrigem, moedaDestino, valor);
                resultado.setText("Resultado: " + String.format("%.2f", resultadoConversao) + " " + moedaDestino);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage());
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static double converterMoeda(String moedaOrigem, String moedaDestino, double valor) throws Exception {
        // Monta a URL da API
        String url = BASE_URL + moedaOrigem + "-" + moedaDestino;
        HttpURLConnection conexao = (HttpURLConnection) new URL(url).openConnection();
        conexao.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        StringBuilder resposta = new StringBuilder();
        String linha;
        while ((linha = reader.readLine()) != null) {
            resposta.append(linha);
        }
        reader.close();

        // Processa a resposta JSON
        JSONObject json = new JSONObject(resposta.toString());
        String chave = moedaOrigem + moedaDestino;
        JSONObject taxas = json.getJSONObject(chave);

        double taxaConversao = taxas.getDouble("bid");

        return valor * taxaConversao; // Retorna o valor convertido
    }
}
