import React, { useEffect, useState } from 'react';
import axios from 'axios';

function ItemList() {
  const [items, setItems] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    fetchItems();
  }, []);

  const fetchItems = async () => {
    try {
      const response = await axios.get('/api/items');
      setItems(response.data);
    } catch (error) {
      console.error('Error fetching items:', error);
    }
  };

  const handleBorrow = async (itemId) => {
    try {
      const response = await axios.post('/api/borrow', null, {
        params: { itemId, user: 'anonymous' },
      });
      alert(response.data);
      await fetchItems(); // รีโหลดข้อมูลหลังยืม
    } catch (error) {
      console.error(error);
      alert(error.response?.data || 'เกิดข้อผิดพลาด');
    }
  };

  // กรองรายการตามชื่อ (พิมพ์แล้วกรองทันที)
  const filteredItems = items.filter(item =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="container mt-5">
      <h2 className="mb-4 text-center">ระบบยืมของ</h2>

      <div className="mb-3 d-flex">
        <input
          type="text"
          className="form-control me-2"
          placeholder="ค้นหาชื่อ..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          style={{ width: '400px' }}
        />
      </div>

      <table className="table table-bordered text-center">
        <thead className="table-dark">
          <tr>
            <th>ชื่อ</th>
            <th>หมวดหมู่</th>
            <th>จำนวนคงเหลือ</th>
            <th>การยืม</th>
          </tr>
        </thead>
        <tbody>
          {filteredItems.length > 0 ? (
            filteredItems.map(item => (
              <tr key={item.id}>
                <td>{item.name}</td>
                <td>{item.category}</td>
                <td>{item.quantity}</td>
                <td>
                  <button
                    className="btn btn-sm btn-primary"
                    onClick={() => handleBorrow(item.id)}
                    disabled={item.quantity <= 0}
                  >
                    ยืม
                  </button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4">ไม่พบรายการที่ค้นหา</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
}

export default ItemList;
