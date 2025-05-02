import React, { useEffect, useState } from 'react';
import axios from 'axios';

function BorrowedList() {
  const [records, setRecords] = useState([]);

  useEffect(() => {
    fetchBorrowed();
  }, []);

  const fetchBorrowed = async () => {
    try {
      const response = await axios.get('/api/serviceKhing/borrowed'); // ดึงข้อมูล borrowed_record จาก API
      setRecords(response.data);  // ใช้ข้อมูลจาก response.data (ซึ่งเป็น array ของ borrowedRecord)
    } catch (error) {
      console.error('Error fetching borrowed items:', error);
    }
  };

  const handleReturn = async (id) => {
    try {
      await axios.post(`/api/serviceKhing/return/${id}`);  // ส่งคำขอคืนของที่มี id นี้
      alert('คืนของเรียบร้อยแล้ว');
      fetchBorrowed(); // รีโหลดข้อมูลหลังจากคืนของ
    } catch (error) {
      alert('เกิดข้อผิดพลาดในการคืนของ');
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4 text-center">📋 รายการของที่ถูกยืม</h2>
      {records.length === 0 ? (
        <p className="text-center">ยังไม่มีของที่ถูกยืม</p>
      ) : (
        <table className="table table-bordered text-center">
          <thead className="table-dark">
            <tr>
              <th>ผู้ยืม</th>
              <th>ไอเทม</th>
              <th>วันที่ยืม</th>
              <th>ครบกำหนด</th>
              <th>สถานะ</th>
              <th>คืนของ</th>
            </tr>
          </thead>
          <tbody>
            {records.map((record) => (
              <tr key={record.id}>
                <td>{record.borrowedBy}</td>
                <td>{record.itemId}</td>
                <td>{new Date(record.borrowDate).toLocaleDateString()}</td>
                <td>{new Date(record.dueDate).toLocaleDateString()}</td>
                <td>
                  {new Date(record.dueDate) < new Date()
                    ? '🟥 เกินกำหนด'
                    : '✅ ปกติ'}
                </td>
                <td>
                  <button
                    className="btn btn-danger btn-sm"
                    onClick={() => handleReturn(record.id)}
                  >
                    คืนของ
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default BorrowedList;
