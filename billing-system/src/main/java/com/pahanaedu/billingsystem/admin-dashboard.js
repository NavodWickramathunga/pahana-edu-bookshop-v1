async function checkAuth() {
    try {
        const res = await fetch('/api/users', { credentials: 'include' });
        if (res.status === 401) {
            console.warn('Unauthorized access, redirecting to AdminLogin.html');
            window.location.href = '/AdminLogin.html';
            return false;
        }
        if (!res.ok) throw new Error(`Auth check failed: ${res.status} ${res.statusText}`);
        return true;
    } catch (error) {
        console.error('Auth check failed:', error);
        window.location.href = '/AdminLogin.html';
        return false;
    }
}

async function loadItems() {
    try {
        const res = await fetch('/api/items', { credentials: 'include' });
        if (!res.ok) {
            console.error('Failed to fetch items:', res.status, res.statusText);
            throw new Error(`Failed to fetch items: ${res.status} ${res.statusText}`);
        }
        const items = await res.json();
        const inventoryTable = document.getElementById('inventory-table');
        const recentItemsTable = document.getElementById('recent-items-table');
        inventoryTable.innerHTML = '<tr><th>Item Name</th><th>Price (LKR)</th><th>Stock</th><th>Author</th><th>Image</th><th>Actions</th></tr>';
        recentItemsTable.innerHTML = '<tr><th>Item Name</th><th>Price (LKR)</th><th>Stock</th><th>Author</th><th>Image</th></tr>';

        items.forEach(item => {
            const row = `
                <td>${item.name}</td>
                <td>${item.price.toFixed(2)}</td>
                <td>${item.stock}</td>
                <td>${item.author}</td>
                <td><img src="${item.imageUrl || '/Uploads/books/default.jpg'}" alt="${item.name}" class="table-image"></td>
                <td><button class="delete-item" data-id="${item.id}">Delete</button></td>
            `;
            inventoryTable.insertAdjacentHTML('beforeend', row);
            if (items.indexOf(item) >= items.length - 5) {
                recentItemsTable.insertAdjacentHTML('beforeend', row.replace('<td><button class="delete-item" data-id="' + item.id + '">Delete</button></td>', ''));
            }
        });

        document.querySelectorAll('.delete-item').forEach(button => {
            button.addEventListener('click', async () => {
                const id = button.getAttribute('data-id');
                if (!confirm('Are you sure you want to delete this item?')) return;
                try {
                    const res = await fetch(`/api/items/${id}`, { method: 'DELETE', credentials: 'include' });
                    if (!res.ok) throw new Error(`Failed to delete item: ${res.status} ${res.statusText}`);
                    await loadItems();
                    alert('Item deleted successfully');
                } catch (error) {
                    alert('Error deleting item: ' + error.message);
                    console.error('Error deleting item:', error);
                }
            });
        });
    } catch (error) {
        alert('Error loading items: ' + error.message);
        console.error('Error loading items:', error);
    }
}

async function loadUsers() {
    try {
        const res = await fetch('/api/users', { credentials: 'include' });
        if (!res.ok) {
            console.error('Failed to fetch users:', res.status, res.statusText);
            throw new Error(`Failed to fetch users: ${res.status} ${res.statusText}`);
        }
        const users = await res.json();
        const tbody = document.getElementById('users-table');
        tbody.innerHTML = '<tr><th>ID</th><th>Username</th><th>Email</th><th>Role</th><th>Mobile</th><th>Actions</th></tr>';

        users.forEach(user => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>${user.mobileNumber || ''}</td>
                <td><button class="delete-user" data-id="${user.id}">Delete</button></td>
            `;
            tbody.appendChild(tr);
        });

        document.querySelectorAll('.delete-user').forEach(button => {
            button.addEventListener('click', async () => {
                const id = button.getAttribute('data-id');
                if (!confirm('Are you sure you want to delete this user?')) return;
                try {
                    const res = await fetch(`/api/users/${id}`, { method: 'DELETE', credentials: 'include' });
                    if (!res.ok) throw new Error(`Failed to delete user: ${res.status} ${res.statusText}`);
                    await loadUsers();
                    alert('User deleted successfully');
                } catch (error) {
                    alert('Error deleting user: ' + error.message);
                    console.error('Error deleting user:', error);
                }
            });
        });
    } catch (error) {
        alert('Error loading users: ' + error.message);
        console.error('Error loading users:', error);
    }
}

async function loadBanner() {
    try {
        const res = await fetch('/api/banner', { credentials: 'include' });
        if (!res.ok) {
            console.error('Failed to fetch banner:', res.status, res.statusText);
            throw new Error(`Failed to fetch banner: ${res.status} ${res.statusText}`);
        }
        const banner = await res.json();
        if (banner.imageUrl) {
            document.getElementById('current-banner').src = banner.imageUrl;
        }
    } catch (error) {
        console.warn('No banner found or error loading banner:', error);
    }
}

document.addEventListener('DOMContentLoaded', async () => {
    if (!(await checkAuth())) return;

    // Handle item form submission
    document.getElementById('add-item-form').addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        try {
            const res = await fetch('/api/items', {
                method: 'POST',
                body: formData,
                credentials: 'include'
            });
            if (!res.ok) throw new Error(`Failed to add item: ${res.status} ${res.statusText}`);
            await loadItems();
            alert('Item added successfully');
            e.target.reset();
        } catch (error) {
            alert('Error adding item: ' + error.message);
            console.error('Error adding item:', error);
        }
    });

    // Handle user form submission
    document.getElementById('add-user-form').addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        try {
            const res = await fetch('/api/users', {
                method: 'POST',
                body: JSON.stringify(Object.fromEntries(formData)),
                headers: { 'Content-Type': 'application/json' },
                credentials: 'include'
            });
            if (!res.ok) throw new Error(`Failed to add user: ${res.status} ${res.statusText}`);
            await loadUsers();
            alert('User added successfully');
            e.target.reset();
        } catch (error) {
            alert('Error adding user: ' + error.message);
            console.error('Error adding user:', error);
        }
    });

    // Handle banner form submission
    document.getElementById('upload-banner-form').addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        try {
            const res = await fetch('/api/banner', {
                method: 'POST',
                body: formData,
                credentials: 'include'
            });
            if (!res.ok) throw new Error(`Failed to upload banner: ${res.status} ${res.statusText}`);
            await loadBanner();
            alert('Banner uploaded successfully');
            e.target.reset();
        } catch (error) {
            alert('Error uploading banner: ' + error.message);
            console.error('Error uploading banner:', error);
        }
    });

    // Load data
    await loadItems();
    await loadUsers();
    await loadBanner();
});