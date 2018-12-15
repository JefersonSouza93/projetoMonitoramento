
public class Main {

    public static void main(String Args[]){

        try{
            RequestClass request1 = new RequestClass();
            request1.id = 1;
            FuncaoGetHistoricoSupermercado xablau = new FuncaoGetHistoricoSupermercado();
            xablau.handleRequest(request1, null);
        }catch(Exception e){
            e.printStackTrace();
        } finally{
        }

    }
}
