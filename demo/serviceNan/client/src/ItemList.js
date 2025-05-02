import React, { useEffect, useState } from 'react';
import axios from 'axios';

function ItemList() {
  const [items, setItems] = useState([]);
  const [filteredItems, setFilteredItems] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    axios.get('/api/items')
      .then(response => {
        setItems(response.data);
        setFilteredItems(response.data);
      })
      .catch(error => console.error('Error fetching items:', error));
  }, []);

  useEffect(() => {
    const filtered = items.filter(item =>
      item.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
    setFilteredItems(filtered);
  }, [searchTerm, items]);

  const borrowItem = (itemId) => {
    axios.post(`/api/borrow`, null, { params: { itemId } })
      .then(response => {
        alert(response.data);
        // Update quantity after borrowing
        axios.get('/api/items')
          .then(res => {
            setItems(res.data);
            setFilteredItems(res.data.filter(item =>
              item.name.toLowerCase().includes(searchTerm.toLowerCase())
            ));
          });
      })
      .catch(error => {
        console.error(error);
        alert(error.response?.data || "เกิดข้อผิดพลาด");
      });
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4">รายการสิ่งของที่สามารถยืมได้</h2>

      <div className="mb-3">
        <input
          type="text"
          className="form-control"
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
          {filteredItems.map(item => (
            <tr key={item.id}>
              <td>{item.name}</td>
              <td>{item.category}</td>
              <td>{item.quantity}</td>
              <td>
                <button
                  className="btn btn-sm btn-primary"
                  onClick={() => borrowItem(item.id)}
                  disabled={item.quantity <= 0}
                >
                  ยืม
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ItemList;
