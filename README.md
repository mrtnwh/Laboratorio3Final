# Final Laboratorio III
Este trabajo final es la implementación de un conjunto de API’s REST en la
plataforma Java, utilizando el framework Spring Boot.



## Deployment

Para correr el proyecto se necesitan hacer estos pasos:

Clonar el repositorio en su directorio local con el siguiente comando.

```bash
  git clone https://github.com/mrtnwh/Laboratorio3Final
```


Tener descargado e instalado postman y entrar a este link:

```bash
  https://www.postman.com/winter-firefly-179270/workspace/laboratorio-3/collection/17916692-50964caf-0075-44d3-8da5-5da22d25d64c?action=share&creator=17916692
```

Y por ultimo ejecutar el archivo: Laboratorio3FinalApplication.java



## CRUD
## Categoria
#### Crear categoria.

```http
    POST: /categoria
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `nombre` | `String` | El nombre de la categoria.|
| `id` | `int` | El id de la categoria. **Required** |
| `descripcion` | `String` | La descripcion de la categoria.|
| `listaProductos` | `ArrayList<Producto>` | En esta se almaceneran los productos. |


#### Eliminar Categoria.

```http
    DEL /categoria/{id} 
```


#### Editar Categoria.

```http
    PUT /categoria/{id} 
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `nombre` | `String` | El nombre de la categoria.|
| `id` | `int` | El id de la categoria. |
| `descripcion` | `String` | La descripcion de la categoria.|
| `listaProductos` | `ArrayList<Producto>` | En esta se almaceneran los productos.


#### Obtener Categoria por id.

```http
    GET /categoria/{id} 
```

#### Obtener todos los productos ordenados por precio (ascendente o descendente).

```http
    GET: /categoria?oder_price=asc (u order_price=desc)
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `int` | El id de la categoria. **Required** |


#### Obtener los productos filtrando por marca.


```http
    GET: /categoria?marca=Motorola
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `int` | El id de la categoria. **Required** |


#### Obtener productos filtrando por precio (minimo y maximo).


```http
    GET: /categoria?minimo=1&maximo=4000
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `int` | El id de la categoria. **Required** |



## Producto

#### Crear Producto.

```http
    POST: /producto
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `nombre` | `String` | El nombre del producto.|
| `categoria` | `int` | La descripcion del producto |
| `id` | `int` | La descripcion del producto.|
| `tipo` | `String` | Tipo de producto (ejemplo: Celular). |
| `categoriaId` | `int` | El id de la categoria. |
| `marca` | `String` | Marca del producto. |
| `modelo` | `String` | Modelo del producto. |
| `precioLista` | `int` | Precio del producto. |

#### Eliminar Producto.

```http
    DEL: /producto/{id}
```

#### Editar Producto.

```http
    PUT: /producto
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `nombre` | `String` | El nombre del producto.|
| `categoria` | `int` | La descripcion del producto |
| `id` | `int` | La descripcion del producto.|
| `tipo` | `String` | Tipo de producto (ejemplo: Celular). |
| `categoriaId` | `int` | El id de la categoria. |
| `marca` | `String` | Marca del producto. |
| `modelo` | `String` | Modelo del producto. |
| `precioLista` | `int` | Precio del producto. |

#### Obtener un producto por su id.

```http
    GET : /producto/{id}
```

#### Obtener un producto por alguno de sus atributos: tipo, marca, categoria.

```http
    GET: /producto?tipo=celular&marca=motorola&categoria=smartphones
```


