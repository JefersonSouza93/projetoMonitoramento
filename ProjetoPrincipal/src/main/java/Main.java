
public class Main {

    public static void main(String Args[]){

        try{
            RequestClass request1 = new RequestClass();
            request1.latitudeMin = -25;
            request1.latitudeMax = -10;
            request1.longitudeMin = -50;
            request1.longitudeMax = -40;
            FuncaoGetSupermercadosEmRegiao xablau = new FuncaoGetSupermercadosEmRegiao();
            xablau.handleRequest(request1, null);
        }catch(Exception e){
            e.printStackTrace();
        } finally{
        }

    }
}
