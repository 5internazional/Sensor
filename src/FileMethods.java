import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileMethods {
    DataOutputStream dos = null;
    FileOutputStream fos = null;
    String filename = "sensor.txt";
    InputStream is = null;
    DataInputStream dis = null;
    String readName = "";
    long[] l = null;
    float [][] f = null;
    //create file

    public void createFile(){
        try {
            fos = new FileOutputStream(filename);
        } catch (FileNotFoundException ex) {
                System.err.println("couldn’t open file - fatal");
                System.exit(0); // brutal exception handling
    }}
    //write in file
    public void writeFile(String sensorName, long[] x, float[][]y){
        try {
            fos = new FileOutputStream(filename);
            dos = new DataOutputStream(fos);
            dos.writeUTF(sensorName);

        int timeStampsNumber = x.length;
        dos.writeInt(timeStampsNumber);

        for (int i = 0; i < timeStampsNumber; i++) {
            dos.writeLong(x[i]);

            int valuesNumber = y[i].length;
            dos.writeInt(valuesNumber);
            for (int j = 0; j < valuesNumber; j++) {
                dos.writeFloat(y[i][j]);
            }
        }
        } catch (IOException ex) {
        System.err.println("couldn’t write data (fatal)");
        System.exit(0);
    }}
    //read from file
    public void readFile(long [] x){
        try{
        is = new FileInputStream(filename);
        dis =  new DataInputStream(is);
        readName = dis.readUTF();

        int timeStampsNumber = dis.readInt();
        l = new long[timeStampsNumber];
        f = new float[timeStampsNumber][];
        for(int i = 0; i<timeStampsNumber; i++){
            x[i] = dis.readLong();
            int valuesNums = dis.readInt();
            float [] set = new float[valuesNums];
            for(int j = 0; j<valuesNums; j++){
                set[j] = dis.readFloat();
            }
            f[i]=set;
        }
    } catch (FileNotFoundException ex) {
        System.err.println("Couldn’t open file (fatal)");
        System.exit(0);
    } catch (IOException ex) {
        System.err.println("couldn’t read data (fatal)");
        System.exit(0);
    }
            try {
        dis.close();
    } catch (IOException ex) {
        System.err.println("Couldn’t close file (fatal)");
        System.exit(0);
    }
    //output for users
            System.out.println("Sensorname " + readName);
            for(int i=0; i<x.length; i++){
        System.out.println("Time: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").format(new Date(x[i])));
        for(int j=0; j<f[i].length; j++){
            System.out.println((j+1) + ":" + f[i][j]);
        }
    }


}}
