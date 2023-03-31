
package com.mycompany.projectds;

/**
 *
 * @author Elifnaz ARICI
 */
public class ElifnazArici_myNode {
    
    int data; // Karşılaştırma, yazdırma ve başlangıçta atama yaparken kullanılır
    int valueOfColumn; // Yazdırırken ve karşılaştırma yaparken kullanılır
    int valueOfRow; // Yazdırırken ve karşılaştırma yaparken kullanılır
    boolean written; // Sadece yazdırırken yardımcı olarak kullanılır

    ElifnazArici_myNode next; // Linked list içinde bağlantıları sağlar ve
                             //nodenin sonraki elemanını gösterir
    ElifnazArici_myNode previous; // Linked list içinde bağlantıları sağlar ve
                                 // node'nin önceki elemanını gösterir
    public ElifnazArici_myNode(int data) {

        this.data = data;
        this.valueOfColumn = 0;
        this.valueOfRow = 0;

        this.next = null;
        this.previous = null;
        this.written = false;

    }
    
    
    
    
    
    
    
    
    
    
}
