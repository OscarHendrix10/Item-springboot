# Item-springboot

## esta es una aplicacion de entrenamiento de spring boot
comando para ejecutar la aplicacion 

mvn.cmd spring-boot:run

# contenido
## modelos, repositorios,  servicios, controladores y run

Cada modulo contiene sus modelos, repositorios, controladores y servicios 
cada uno esta conectado entre si para mayor distribucion


## API Referencias

#### Get all items

```http
  GET /items/getAll
```

| Parametro | Tipo    | Descripcion                |
| :-------- | :------- | :------------------------- |
| `NA` | `NA` | obitiene todos los items de la base de datos  |

#### Get item

```http
  GET /items/get/${id}
```

| Parametro | Tipo     | Descripcion                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | Requiere de ID  |

#### add item

```http
  POST /items/add
```
### Es en formato JSON
| Parametro | Tipo     | Descripcion                    |
| :-------- | :------- | :-------------------------------- |
| `name`      | `string` | agrega un nuevo registro a la base de datos |

#### edit item
```http
  PUT /items/edit/${id}
```
### Es en formato JSON
| Parametro | Tipo     | Descripcion                    |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | Es requerido el id|
| `name`      | `string` | edita el registro seleccionado de la base de datos|

#### delete item

```http
  DELETE /items/delete/${id}
```

| Parametro | Tipo     | Descripcion                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | Requiere de ID  |

#### Get all persons

```http
  GET /person/getAll
```

| Parametro | Tipo    | Descripcion                |
| :-------- | :------- | :------------------------- |
| `NA` | `NA` | obitiene todos las personas de la base de datos  |

#### Get person

```http
  GET /person/get/${id}
```

| Parametro | Tipo     | Descripcion                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | Requiere de ID  |

#### add person

```http
  POST /person/add
```
### Es en formato JSON
| Parametro | Tipo     | Descripcion                    |
| :-------- | :------- | :-------------------------------- |
| `nombre`      | `string` | nombre de la persona |
| `apellido`      | `string` | apellido de la persona |
| `edad`      | `int` | edad de la persona |

#### edit person
```http
  PUT /person/edit/${id}
```
### Es en formato JSON
| Parametro | Tipo     | Descripcion                    |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | Es requerido el id|
| `nombre`      | `string` | nombre de la persona |
| `apellido`      | `string` | apellido de la persona |
| `edad`      | `int` | edad de la persona |

#### delete person

```http
  DELETE /person/delete/${id}
```

| Parametro | Tipo     | Descripcion                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | Requiere de ID  |


#### Get all customers

```http
  GET /customers/getAll
```

| Parametro | Tipo    | Descripcion                |
| :-------- | :------- | :------------------------- |
| `NA` | `NA` | obitiene todos los clientes de la base de datos  |

#### Get customer

```http
  GET /customers/get/${id}
```

| Parametro | Tipo     | Descripcion                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | Requiere de ID  |

#### add customer

```http
  POST /customers/add
```
### Es en formato JSON
| Parametro | Tipo     | Descripcion                    |
| :-------- | :------- | :-------------------------------- |
| `email`      | `string` | correo de la cuenta |
| `password`      | `string` | password de la cuenta |
| `person_id`      | `Person` | objeto que tiene la persona seleccionada |
| `status`      | `boolean` | edad de la persona |

#### edit customer
```http
  PUT /customers/edit/${id}
```
### Es en formato JSON
| Parametro | Tipo     | Descripcion                    |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | Es requerido el id|
| `email`      | `string` | correo de la cuenta |
| `password`      | `string` | password de la cuenta |
| `person_id`      | `Person` | objeto que tiene la persona seleccionada |
| `status`      | `boolean` | edad de la persona |

#### delete customer

```http
  DELETE /customers/delete/${id}
```

| Parametro | Tipo     | Descripcion                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | Requiere de ID  y desactiva al cliente no lo elimina |


## comandos de docker utlizados en este projecto

```Powershell
-- creamos un volumen de docker

docker volume create docker_volume

-- creamos un contenedor y descargamos la imagen sql con la base de datos creada y al puerto 3307

docker run --name dockerdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=db_springboot -v docker_volume:/var/lib/mysql -p 3307:3306 -d mysql:5.7
```


## utilizando docker compose

```Powershell
-- primero ejecutamos el build de nuestro projecto para contruir una imagen de docker

docker build -t  springboot_image .

-- ejecutamos este comando primero debemos verificar si el docker-compose.yml este correcto

docker-compose up


-- para revertir los cambios

docker-compose down
```