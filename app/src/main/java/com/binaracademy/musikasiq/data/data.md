Flow dalam mengolah data

1. local dengan room (contoh example room flow)
- membuat room model di room package
- membuat dao di dao package
- membuat repository -> interface buat akses data
repository digunakan untuk dapat diimplementasikan secara local dan remote
- membuat implementasi dari repository interface di repository.local package
menggunakan dependency injection untuk mengakses dao, memanipulasi data
- view model -> view (activity or fragment)


2. remote dengan retrofit (contoh user service flow)
- membuat model di model package
- membuat service retrovit di api packege
- membuat repository -> interface buat akses data
  repository digunakan untuk dapat diimplementasikan secara local dan remote
- membuat implementasi dari repository interface di repository.remote package
  menggunakan dependency injection untuk mengakses service dari api package, memanipulasi data
- view model -> view (activity or fragment)