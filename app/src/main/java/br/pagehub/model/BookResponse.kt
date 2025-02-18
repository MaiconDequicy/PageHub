package br.pagehub.model

data class BookResponse(
    val items: List<BookItem>?  // A lista de livros retornados pela pesquisa
)

data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String?,
    val authors: List<String>?,
    val imageLinks: ImageLinks?
)

data class ImageLinks(
    val thumbnail: String?
)
