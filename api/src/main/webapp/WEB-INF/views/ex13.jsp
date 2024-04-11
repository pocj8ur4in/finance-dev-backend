<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ex12. 자판기 API with DB</title>
    <link rel="stylesheet" href="/css/global.css">
</head>
<body>
<div class="container">
    <h1 lang="ko">ex12. 자판기 API with DB</h1>
    <h3>자판기 상품 조회</h3>

    <p id="productCount">상품 개수: <jsp:getProperty name="productCount" property="count"/>개</p>

    <table id="productTable">
        <thead>
        <tr>
            <th>상품명</th>
            <th>가격</th>
            <th>유통기한</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.date}</td>
                <td><button class="btn btn-edit" onclick="updateProduct('${product.name}', '${product.price}', '${product.date}')">수정</button></td>
                <td><button class="btn btn-delete" onclick="deleteProduct('${product.name}')">삭제</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <button class="btn btn-add" onclick="addProduct()">추가</button>
    <button type="button" onclick="document.location.href = '/'">처음으로</button>
</div>

<div id="modal" class="modal" style="display: none;">
    <form id="modalForm">
        <div class="modal-header">
            <h2 id="modalTitle"></h2>
        </div>
        <div class="form-group">
            <label for="name">상품명</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="price">가격</label>
            <input type="number" id="price" name="price" required>
        </div>
        <div class="form-group">
            <label for="date">유통기한</label>
            <input type="date" id="date" name="date" required>
        </div>
        <div class="button-row">
            <button type="submit" id="modalSubmit"></button>
            <button type="button" onclick="closeModal()">취소</button>
        </div>
    </form>
</div>

<script>
    function addProduct() {
        openModal('상품 생성', '추가', '', '', '');
    }

    function openModal(title, submitText, nameValue, priceValue, dateValue) {
        const modal = document.getElementById('modal');
        const modalForm = document.getElementById('modalForm');
        const modalTitle = document.getElementById('modalTitle');
        const modalSubmit = document.getElementById('modalSubmit');
        const nameInput = document.getElementById('name');
        const priceInput = document.getElementById('price');
        const dateInput = document.getElementById('date');

        modalTitle.innerText = title;
        modalSubmit.innerText = submitText;
        nameInput.value = nameValue;
        priceInput.value = priceValue;
        dateInput.value = dateValue;

        modal.style.display = 'block';

        modal.addEventListener('click', function(event) {
            if (event.target === modal) {
                closeModal();
            }
        });

        modalForm.addEventListener('submit', function(event) {
            event.preventDefault();

            const name = nameInput.value;
            const price = priceInput.value;
            const date = dateInput.value;

            const requestData = {
                product : {
                    name: name,
                    price: price,
                    date: date
                }
            };

            fetch('/v1/api/ex12/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(requestData)
            })
                .then(response => {
                    if (response.ok) {
                        location.reload();
                    } else {
                        alert('추가 실패');
                    }
                })
                .catch(error => {
                    console.error('에러:', error);
                    alert('요청을 처리하는 동안 오류가 발생했습니다.');
                });
        });
    }

    function closeModal() {
        const modal = document.getElementById('modal');
        modal.style.display = 'none';
    }

    function deleteProduct(name) {
        if (confirm('정말 삭제하시겠습니까?')) {
            fetch('/v1/api/ex12/delete', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ name: name })
            })
                .then(response => {
                    if (response.ok) {
                        location.reload();
                    } else {
                        alert('삭제 실패');
                    }
                })
                .catch(error => {
                    console.error('에러:', error);
                    alert('요청을 처리하는 동안 오류가 발생했습니다.');
                });
        }
    }

    function updateProduct(name, price, date) {
        openModal('상품 수정', '수정', name, price, date);
    }
</script>
</body>
</html>
