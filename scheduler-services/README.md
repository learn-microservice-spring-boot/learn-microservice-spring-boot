# Kafka

### Running Kafka local
``
$ .\bin\windows\kafka-server-start.bat config\server.properties
``

### Must running for create topic
``
$ .\bin\windows\zookeeper-server-start.bat config\zookeeper.properties
``

#### Membuat UUID untuk memformat jika ingin membuat folder log custom
#### open folder kafka menggunakan VSCode, buat folder sesuai yang di inginkan di dalam folder kafka

``
$ config > kraf > server.properties >>> "log.dirs=NAMA_FOLDER_DI_INGINKAN"
``

``
$ .\bin\windows\kafka-storage.bat random-uuid
``

``
$ .\bin\windows\kafka-storage.bat format --cluster-id UUID_YANG_DIHASILKAN --config config\kraft\server.properties
``

## Check List All group and Topic
``
$ .\bin\windows\kafka-consumer-groups.bat --bootstrap-server localhost:9092 --all-groups --all-topics --describe
``


##### Create Topic
``
$ .\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --create --topic belajarkfk
``

## Producer
``
$ .\bin\windows\kafka-console-producer.bat --bootstrap-server localhost:9092 --topic belajarkfk
``

## Consumer run
#### gunakan salah satu yang di inginkan, sesuai kebutuhan
``
$ .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic belajarkfk
``

``
$ .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic belajarkfk --from-beginning
``

``
$ .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic belajarkfk --group belajar --from-beginning
``

## Check Partition
``
$ .\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --describe --topic belajarkfk
``
#### update partition dari 1 ke 2 setelahnya chek patitions lagi menggunakan di atas ini.
``
$ .\bin\windows\kafka-topics.bat --bootstrap-server localhost:9092 --alter --topic belajarkfk --partitions 2
``

## Routing
#### saat menentukan partition yang akan di pilih kafka menggunakan rumus :
#### > has(message.key) % total partition
#### > misal ketika mengirim message dengan key "ari", dengan jumlah partition 2
#### > maka, partition yang di pilih adalah:
#### > has(ari) % 2
#### misal has(ari) = 8 artinya :
#### 8 % 2 = 0
#### maka partition akan di simpan di partition 0 atau 1

``
$
``

``
$
``

``
$
``

``
$
``

``
$
``

``
$
``

``
$
``

``
$
``

``
$
``

``
$
``

``
$
``

``
$
``

``
$
``

``
$
``