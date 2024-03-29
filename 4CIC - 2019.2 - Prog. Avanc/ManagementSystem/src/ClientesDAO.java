import java.sql.*;
import java.util.*; //List

public class ClientesDAO {
    
    public static Statement stClientes; //executa SQL
    public static ResultSet rsClientes; //armazena result do select
    
    public static void cadastrarCliente(ClientesVO tmpCliente) throws Exception{
        
        try{
            ConexaoDAO.abrirConexao();
            //montagem do insert
            String sqlCadCli = "";
            sqlCadCli += "Insert into clientes(";
            sqlCadCli += "cpf_CLIENTE, nome_CLIENTE,";
            sqlCadCli += "dataNascimento_CLIENTE, endereco_CLIENTE,";
            sqlCadCli += "bairro_CLIENTE, cidade_CLIENTE,";
            sqlCadCli += "telefone_CLIENTE, email_CLIENTE,";
            sqlCadCli += "foto_CLIENTE, status_CLIENTE)";
            sqlCadCli += "values(";
            sqlCadCli += "'" + tmpCliente.getCpf() + "',";
            sqlCadCli += "'" + tmpCliente.getNome() + "',";
            sqlCadCli += "'" + tmpCliente.getDataNascimento()+ "',";
            sqlCadCli += "'" + tmpCliente.getEndereco()+ "',";
            sqlCadCli += "'" + tmpCliente.getBairro() + "',";
            sqlCadCli += "'" + tmpCliente.getCidade() + "',";
            sqlCadCli += "'" + tmpCliente.getTelefone()+ "',";
            sqlCadCli += "'" + tmpCliente.getEmail()+ "',";
            sqlCadCli += "'" + tmpCliente.getFoto()+ "',1)";
            
            //preparando statement para execução do INSERT
            stClientes = ConexaoDAO.connSistema.createStatement();
                    
            //execução do insert
            stClientes.executeUpdate(sqlCadCli);
            
            ConexaoDAO.fecharConexao();
            
        }catch(Exception erro){
            String msg = "Falha no procedimento de cadastro de cliente.\n"
                       + "Verifique a sintaxe da instrução Insert e nomes de campos e tabelas.\n\n"
                       + "Erro Original: " + erro.getMessage();
            
            throw new Exception(msg);
        }
    }//fechando cadastrarCliente
    
    public static ClientesVO consultarCliente(String tmpCpf) throws Exception{
        
         try{
            ConexaoDAO.abrirConexao();
            
            ClientesVO tmpCliente = new ClientesVO();
            
            String sqlConsulta = "Select * from clientes where cpf_CLIENTE like '" + tmpCpf + "'";
            
            //preparando statement
            stClientes = ConexaoDAO.connSistema.createStatement();
            rsClientes = stClientes.executeQuery(sqlConsulta);
            
            if(rsClientes.next()){//se houver registros
                
                tmpCliente.setCpf(rsClientes.getString("cpf_CLIENTE"));
                tmpCliente.setNome(rsClientes.getString("nome_CLIENTE"));
                tmpCliente.setDataNascimento(rsClientes.getString("dataNascimento_CLIENTE"));
                tmpCliente.setEndereco(rsClientes.getString("endereco_CLIENTE"));
                tmpCliente.setBairro(rsClientes.getString("bairro_CLIENTE"));
                tmpCliente.setCidade(rsClientes.getString("cidade_CLIENTE"));
                tmpCliente.setTelefone(rsClientes.getString("telefone_CLIENTE"));
                tmpCliente.setEmail(rsClientes.getString("email_CLIENTE"));
                tmpCliente.setFoto(rsClientes.getString("foto_CLIENTE"));
                tmpCliente.setStatus(rsClientes.getInt("status_CLIENTE"));
                
                ConexaoDAO.fecharConexao();
                return tmpCliente;
            }
            
            ConexaoDAO.fecharConexao();
            return null; // saida 1 - return            
            
        }catch(Exception erro){
            String msg = "Falha na consulta do Cliente.\n"
                       + "Verifique a sintaxe da instrução Select e nomes de campos e tabelas.\n\n"
                       + "Erro Original: " + erro.getMessage(); 
            
            throw new Exception(msg); //saida 2
        }                     
        
    }//fechando consultar
    
    public static List<ClientesVO> listarClientes(int tmpTipo, String tmpBusca) throws Exception{
                
        try{
            ConexaoDAO.abrirConexao();
            
            List<ClientesVO> lstClientes = new ArrayList<ClientesVO>();
            
            String sqlLista = "";
            
            if (tmpTipo == 0){
                sqlLista = "Select * from clientes";
                
            }else if (tmpTipo == 1){
                sqlLista = "Select * from clientes where bairro_CLIENTE like '" + tmpBusca + "'";
                
            }else if(tmpTipo == 2){
                sqlLista = "Select * from clientes where nome_CLIENTE like '%" + tmpBusca + "%'";
                
            }else if (tmpTipo == 3){
                sqlLista = "Select * from clientes where nome_CLIENTE like '" + tmpBusca + "%'";
            }
            
            //preparando statement
            stClientes = ConexaoDAO.connSistema.createStatement();
            rsClientes = stClientes.executeQuery(sqlLista);
            
            while(rsClientes.next()){
                
                ClientesVO tmpCliente = new ClientesVO();//instanciando obj Cliente
                
                tmpCliente.setCpf(rsClientes.getString("cpf_CLIENTE"));
                tmpCliente.setNome(rsClientes.getString("nome_CLIENTE"));
                tmpCliente.setDataNascimento(rsClientes.getString("dataNascimento_CLIENTE"));
                tmpCliente.setEndereco(rsClientes.getString("endereco_CLIENTE"));
                tmpCliente.setBairro(rsClientes.getString("bairro_CLIENTE"));
                tmpCliente.setCidade(rsClientes.getString("cidade_CLIENTE"));
                tmpCliente.setTelefone(rsClientes.getString("telefone_CLIENTE"));
                tmpCliente.setEmail(rsClientes.getString("email_CLIENTE"));
                tmpCliente.setFoto(rsClientes.getString("foto_CLIENTE"));
                tmpCliente.setStatus(rsClientes.getInt("status_CLIENTE"));
                
                lstClientes.add(tmpCliente);                
            }
            
            ConexaoDAO.fecharConexao();
            return lstClientes; // saida 1 - return            
            
        }catch(Exception erro){
            String msg = "Falha na listagem dos dados.\n"
                       + "Verifique a sintaxe da instrução Select e nomes de campos e tabelas.\n\n"
                       + "Erro Original: " + erro.getMessage(); 
            
            throw new Exception(msg); //saida 2
        }             
        
    }//fechando listarClientes - saida 3
    
    public static void alterarCliente() throws Exception{
        
    }
    
    public static void desativarCliente() throws Exception{
        
    }
            
    
}
