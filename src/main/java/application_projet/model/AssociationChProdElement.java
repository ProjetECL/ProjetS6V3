package application_projet.model;

import application.fichiers.CSVV;
import org.apache.commons.csv.CSVRecord;
import utils.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AssociationChProdElement {

    private static final String TAG = "AssociationChProdElement";
    private final static String csvPath = "src/associationChProdEle.csv";
    private String idChaineProd;
    private String idElement;
    private int quantite;
    private TypeElement typeElement;

    public AssociationChProdElement(String idChaineProd, String idElement, int quantite, TypeElement typeElement) {
        this.idChaineProd = idChaineProd;
        this.idElement = idElement;
        this.quantite = quantite;
        this.typeElement = typeElement;
    }

    public String getIdChaineProd() {
        return idChaineProd;
    }

    public void setIdChaineProd(String idChaineProd) {
        this.idChaineProd = idChaineProd;
    }

    public String getIdElement() {
        return idElement;
    }

    public void setIdElement(String idElement) {
        this.idElement = idElement;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }

    public void setTypeElement(TypeElement typeElement) {
        this.typeElement = typeElement;
    }

    @Override
    public String toString() {
        return "AssociationChProdElement{" +
                "idChaineProd='" + idChaineProd + '\'' +
                ", idElement='" + idElement + '\'' +
                ", quantite=" + quantite +
                ", typeElement=" + typeElement +
                '}';
    }

    public static List<AssociationChProdElement> csvVersAssociation(){
        List<AssociationChProdElement> list = new ArrayList<>();

        CSVV.lecture(csvPath).forEach(csvRecord -> {
           // Log.d(TAG,csvRecord.toMap().toString());
            int qte = Integer.parseInt(csvRecord.get("qte"));
            TypeElement type = TypeElement.valueOf(csvRecord.get("type"));
            list.add(new AssociationChProdElement(csvRecord.get("codeCh"),csvRecord.get("codeEle"),
                    qte,type));
        });
        return list;
    }

}
