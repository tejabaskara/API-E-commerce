# API-E-commerce

## PENJELASAN
merupakan API untuk melakukan perubahan di dalam database untuk e-commerce. API ini dapat menghandle methode PUT, GET, DELETE, dan juga POST. 

##API KEY
API KEY pada API ini adalah "x-api-key"
VALUE pada API ini adalah "niceguy_bmljZWd1eq"

## CARA MENGGUNAKAN PROGRAM
untuk dapat menggunakan program ini pada terminal anda, anda diharuskan untuk memiliki program-program berikut pada perangkat anda:
1. [GIT](https://git-scm.com/downloads)
2. [Java](https://www.techspot.com/downloads/5553-java-jdk.html) versi 11
3. [Postman](https://www.postman.com/downloads/)


Jika anda sudah memiliki semua program diatas anda dapat mengikuti cara di bawah ini:
1. _clone_ GIT [Repository](https://github.com/tejabaskara/API-E-commerce.git) ini
2. lalu jalankan program.
3. buka postman dan masukkan http://localhost:8065/users pada request.



## CARA KERJA PROGRAM
program bekerja jika API dijalankan. API dapat digunakan dengan menggunakan postman untuk melakukan request ke API. Methode request yang dapat dilakukan pada API ini adalah GET, PUT, POST, dan juga DELETE. Untuk bagian request PUT dan juga POST pengguna perlu mengirimkan JSON file ke API. Untuk DELETE pengguna perlu menambahkan id di dalam path.

## JSON

1. users
```javascript
{ 
  "id" : id,
  "first_name" : "first name",
  "last_name" : "last name",
  "email" :"example@example.com",
  "phone_number" :"5613256532153671",
  "type" : "seller/buyer"
}
```
2. products
```javascript
{ 
  "id" : id,
  "seller" : seller,
  "stock" : seller,
  "description" :"description",
  "price" :"Rp 317273913",
  "title" : "nama barang" 
}
```
3. addresses
```javascript
{ 
  "users" : user,
  "type" : "type",
  "line1" : "line1",
  "line2" :"line2",
  "province" :"province",
  "city" : "city",
  "postcode":"postcode"
}
```
4. reviews
```javascript
{ 
  "order" : order,
  "description" : "description",
  "star" : star
}
```
5. orders
```javascript
{ 
  "id" : id,
  "buyer" : buyer,
  "note" : note,
  "total" : total,
  "discount" : discount,
  "is_paid" : "true/false"
}
```
6. orders detail
```javascript
{ 
  "order" : order,
  "product" : product,
  "quantity" : quantity,
  "price" : price
}
```

## GAMBAR PENGGUNAAN PROGRAM
![GET](https://drive.google.com/uc?id=1jWYWspDoPw5WaHEBi6Wil4BJKF7FQtac)

![GET /path/](https://drive.google.com/uc?id=1Hhdk2vcSTBCcIF4CEBxi8rbx3LKdmgeb)

![GET query filter](https://drive.google.com/uc?id=12gygoeb1VJk2jzB5DgU3eALDAW-RrKK1)

![Wrong POST](https://drive.google.com/uc?id=1VpHDhQKS6TRAv1oGaiwmy13JPecr3mDV)

![POST](https://drive.google.com/uc?id=11NCoWJAMddWjAAaC6VHWpelO3DWtohFl)

### PEMBUAT
@tejabaskara
