
# Pet hospital schedule springboot

Software as a Service application that provides a scheduling interface for a small pet hospital that takes care of animals. This will allow the small pet hospital  create pets, owners, and employees, and then schedule events for employees to provide services for pets.





## API Reference

#### Save Customer

```http
  POST /user/customer
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `name` | `string` | **Required** |
| `phoneNumber` | `string` | **Required** |

#### Save Pet

```http
  POST /pet
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `type`      | `string` | **Required** |
| `name`      | `string` | **Required** |
| `birthDate`      | `date` | **Required**. Ex: "2019-12-16T04:43:57.995Z"|
| `ownerId`      | `int` | **Required**|
| `notes`      | `string` | **Optional**|




## Tech Stack

**Server:** Spring boot 2.2.2, java 8, mysql

