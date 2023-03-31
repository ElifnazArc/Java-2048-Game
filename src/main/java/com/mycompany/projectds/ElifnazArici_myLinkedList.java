
package com.mycompany.projectds;

/**
 *
 * @author Elifnaz ARICI
 */
public class ElifnazArici_myLinkedList {
    
    ElifnazArici_myNode headInfo; // Listenin başlangıçtaki elemanını tutar 
    ElifnazArici_myNode tailInfo; // Listenin en son eklenen elemanını tutar

    int i = 0; // Arraylerin kaçıncı elemanını çağırdığımızı gösterir
    int listSize = 0; // Linked List'in boyutunu tutar.
    int presentColumn = 0; // En son hangi columna ekleme yapıldıysa o columnu tutar. 

    // Sırayla eklenen nodelerin hangi sütuna gideceğinin bilgisini tutar.
    int[] columnArray = {1, 4, 2, 3, 5, 2, 5, 1, 1, 2, 3, 3, 4, 2, 3, 1, 5, 3, 2, 4, 4, 4, 4, 2, 3, 3, 3, 3, 3, 2, 2};

    // Sırayla eklenen nodelerin hangi dataya sahip olacağı bilgisini tutar
    int[] dataArray = {2, 2, 4, 2, 4, 2, 4, 8, 8, 32, 2, 64, 16, 64, 32, 16, 16, 32, 64, 8, 4, 2, 2, 2, 64, 32, 16, 8, 8, 4, 8};

////////////////////////////////////////////////////////////////////////////////  
    
    // Begin metodunda oyunu başlatıyoruz ve bitiriyoruz.
    // Aldığımız array bilgisi doğrultusunda oyunda kaç eleman ekleneceği
    // ve elemanların hangi değerleri alacağını biliyoruz.
    // Begin metodu, bu bilgilerle kodu sürdürüyor ve bittiğinde
    // bize bilgilendirme veriyor.
    void begin() {

        System.out.println("************DROP*NUMBER******************");

        addNewNode();
        printList();

        for (int c = 0; c < dataArray.length; c++) {

            if (columnSize(1) == 7 || columnSize(2) == 7 || columnSize(3) == 7
                    || columnSize(4) == 7 || columnSize(5) == 7) {

                System.out.println("***************GAME*OVER***************");
                break;

            } else {

                addNewNode();
                checkColumn();
                printList();

            }

        }

    }
////////////////////////////////////////////////////////////////////////////////

    // ColumSize metodu parametre olarak çağırıldığı sırada oluşturulan
    // node nin column bilgisini alır ve tüm linkedlisti gezerek
    // parametre olarak alınan columnun kaç elemanı olduğunu bulup döndürür
    int columnSize(int columnInfo) {

        int columnSize = 0;

        ElifnazArici_myNode temp = headInfo;

        while (temp != null) {

            if (temp.valueOfColumn == columnInfo) {

                columnSize++;
            }

            temp = temp.next;

        }

        return columnSize;
    }

////////////////////////////////////////////////////////////////////////////////
    
    // addNewNode metodu yukarıdaki arraylarden aldığı data ve column bilgileriyle
    // yeni node için row, column ve data bilgilerini atar. Eğer liste boşsa
    // yukarıda tanımladığım head ve tail bilgilerini yeni node atıp
    // en son liste boyutunu 1 artırır. Eğer listede hali hazırda bir eleman
    // varsa yeni node en sona atıp tailInfo güncelledikten sonra yine listSize
    // 1 arttırır. Bir sonraki adımda array listesinde okumaya devam etsin diye 
    // i elemanını ve presentColumn elemanını da en son 1 arttırıyorum.
    void addNewNode() {

        int value = dataArray[i];

        System.out.println("\nThe number is " + value);

        ElifnazArici_myNode newNode = new ElifnazArici_myNode(value);

        newNode.valueOfColumn = columnArray[i];
        System.out.println("Column Value is " + columnArray[i]);

        newNode.valueOfRow = (columnSize(columnArray[i]) + 1);

        if (headInfo == null) {

            headInfo = newNode;
            tailInfo = headInfo;

        } else {

            tailInfo.next = newNode;
            newNode.previous = tailInfo;
            tailInfo = newNode;

        }
        listSize++;
        i++;
        presentColumn = newNode.valueOfColumn;
    }
////////////////////////////////////////////////////////////////////////////////

    // Genel mantık şu şekilde: elimde 2 tane temp değeri tutuyorum.
    // Bu iki temp değeri aynı liste üstünde dönüyor ama dönerken aynı anda
    // aynı elemana gelmediğinden ve her elemanın kendisi hariç herkesle 
    // karşılaştırıldığından emin oluyorum. 
    // Bu karşılaştırmayı yaparken column bilgisi, row bilgisi ve data bilgisi
    // işlem yapma şartlarıma uyuyor mu kontrol ediyorum. Burada en önemli 
    // nokta bu karşılaştırmayı yapabildiği sürece listeyi en baştan gezmeye
    // devam etmesi. Ta ki tüm listede karşılaştırılacak  ortak data, row
    // ve column bilgisine sahip başka bir eleman kalmayana kadar. 
    void checkColumn() {

        ElifnazArici_myNode temp1 = headInfo;
        ElifnazArici_myNode temp2 = headInfo.next;
        boolean condition = true;

        while (condition) {
            condition = false;

            for (int j = 0; j < listSize; j++) {

                for (int k = 0; k < (listSize - 1 - j); k++) {

                    if (temp1.valueOfColumn == presentColumn && temp2.valueOfColumn == presentColumn) {

                        if (((temp2.valueOfRow) - 1) == temp1.valueOfRow && temp1.data == temp2.data) {

                            condition = true;

                            removeNode(temp2);

                            temp1.data *= 2;

                            break;
                        }
                    }
                    temp2 = temp2.next;
                }
                if (condition == true) {
                    break;
                }

                temp1 = temp1.next;
                temp2 = temp1.next;

                if (temp2 == null) {
                    break;
                }
            }

            temp1 = headInfo;
            temp2 = headInfo.next;

        }
    }
////////////////////////////////////////////////////////////////////////////////

    // numberOfDigits metodu yazdırırken yardımcı olan bir metod
    // yazdırırken düzgün şekilde yazdırabilmek adına
    // elemanların kaç basamaklı olduğunu döndürüyor böylece
    // yazdırma işlemi daha düzenli oluyor.
    int numberOfDigits(int num) {

        int digit = 0;
        while (num > 0) {
            num /= 10;
            digit++;
        }
        return digit;
    }
////////////////////////////////////////////////////////////////////////////////

    // Tüm işlemleri yaptıktan sonra her adımda çıktıyı ekrana 
    // printList metodu yazdırır. MEtodun çalışması oldukça basit: iç içe
    // iki for döngüsüyle row ve column bilgisini bildiğimiz gridi sırayla 
    // dolaşıyor ve dolşaırken her bir adımda linkedlist içince dolaşıp
    // o sırada tuttuğumuz node nin row ve coolumn bilgisinin for döngüsünde
    // kesişimi olduğu noktalara node datasını yazdırıyor. Kesişen bir eleman
    // yoksa o kısmı boş geçiyor ve bu işlemi toplam 7x5 kes yaptıktan sonra
    // yazdırma işlemini sonlandırıyor.
    void printList() {

        ElifnazArici_myNode temp;
        int value;
        boolean condition = false;

        for (int row = 7; row > 0; row--) {

            System.out.println(" ___  ___  ___  ___  ___ ");

            for (int clm = 1; clm < 6; clm++) {

                temp = headInfo;

                System.out.print("|");

                for (int node = 0; node < listSize; node++) {

                    value = temp.data;

                    if (temp.valueOfRow == row && temp.valueOfColumn == clm && temp.written == false) {

                        switch (numberOfDigits(value)) {
                            case 1 ->
                                System.out.print(" " + value + " ");
                            case 2 ->
                                System.out.print(" " + value);
                            case 3 ->
                                System.out.print(value);
                        }

                        temp.written = true;
                        condition = true;
                        temp = temp.next;

                    } else {
                        temp = temp.next;
                    }
                }

                if (condition == false) {
                    System.out.print("   ");

                }
                condition = false;

                System.out.print("|");

                if (clm == 5) {
                    System.out.println();
                }

            }

            System.out.println(" ¯¯¯  ¯¯¯  ¯¯¯  ¯¯¯  ¯¯¯ ");
        }

        temp = headInfo;
        for (int node = 0; node < listSize; node++) {
            temp.written = false;
            temp = temp.next;
        }
    }
////////////////////////////////////////////////////////////////////////////////

    // removeNode metodu işlem yapıldıktan sonra altlı üstlü iki nodeden
    // üsttekini linked listin içinden silmeye yarar. Bunun için tüm linked
    // list içinde dolaşarak, parametre olarak aldığımız nodenin row ve 
    // column bilgilerini karşılaştırır ve uyuyorsa o nodenin bağlantılarını
    // koparır ve listeden çıkarır ve listSize -1 yapar. 
    // Böylece o node silinmiş olur.
    void removeNode(ElifnazArici_myNode eraseNode) {

        ElifnazArici_myNode temp = headInfo;

        for (int a = 0; a < listSize; a++) {

            if (eraseNode.valueOfColumn == temp.valueOfColumn && eraseNode.valueOfRow == temp.valueOfRow
                    && eraseNode.data == temp.data) {

                break;

            } else {
                temp = temp.next;
            }
        }

        System.out.println("OPERATION HAS DONE HERE AT " + eraseNode.valueOfColumn + ". COLUMN");

        if (temp == headInfo) {

            headInfo = headInfo.next;

            temp.next = null;
            temp.valueOfColumn = 0;
            temp.valueOfRow = 0;

        } else if (temp == tailInfo) {

            tailInfo = temp.previous;
            tailInfo.next = null;

            temp.valueOfColumn = 0;
            temp.valueOfRow = 0;

        } else {

            temp.previous.next = temp.next;
            temp.next.previous = temp.previous;
            temp.valueOfColumn = 0;
            temp.valueOfRow = 0;

        }
        listSize--;
    }
////////////////////////////////////////////////////////////////////////////////

}
