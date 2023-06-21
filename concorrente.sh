javac -d out ./java/src/leitor/Reader.java \
              ./java/src/sequencial/MergeSort.java \
              ./java/src/concorrente/MergeSortConcorrente.java \

java -cp out concorrente.MergeSortConcorrente D