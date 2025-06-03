# Routes Module API Documentation

## Overview

### Base URL => /routes

## Endpoints

### 1. Create Routes


```http
POST /routes/create
```

#### Access Control:
Required Role: 

#### Path Parameters:

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. user' username |

#### Response Format:
```

```

### 2. Get All Routes
Get Basic Information about all routes

```http
GET /routes/all 
```

#### Access Control:
Required Role:

#### Response Format
```

```

### 3. Get Route By Id
Get Particular Route Detailed Information 

```http
GET /routes/<route-id>
```

#### Access Control
Required Role:

#### path Parameters:
| Parameter    | Type     | Description                           |
|:-------------| :------- |:--------------------------------------|
| `<route-id>` | `string` | **Required**. Unique UUID of an route |

#### Response Object
```

```